package org.example.manager;


import lombok.RequiredArgsConstructor;
import org.example.dto.DealRegisterRequestDTO;
import org.example.dto.DealRegisterResponseDTO;
import org.example.exception.DealNotEnoughException;
import org.example.exception.DealPriceChangedException;
import org.example.exception.DealRegistrationException;
import org.example.exception.OrderNotFoundException;
import org.example.model.DealModel;
import org.example.rowmapper.DealRowMapper;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Component
@RequiredArgsConstructor
public class DealManager {
    private final NamedParameterJdbcTemplate template;
    private final DealRowMapper dealRowMapper;

    @Transactional
    public DealRegisterResponseDTO register(DealRegisterRequestDTO requestDTO) {
        try {
            final DealModel item = template.queryForObject(
                    // language=PostgreSQL
                    """
                            SELECT id, salesman, price, balance_btc, balance_rub, dealstatus
                            FROM deals
                            WHERE id = :id AND removed = FALSE
                            """,
                    Map.of("id", requestDTO.getId()),
                    dealRowMapper
            );

            if (item.getBalance_btc() < requestDTO.getBalance_btc()) {
                throw new DealNotEnoughException("deal with id " + item.getId() + " has bitcoin " + item.getBalance_btc() + " but requested " + requestDTO.getBalance_btc());
            }

            if (item.getPrice() != requestDTO.getPrice()) {
                throw new DealPriceChangedException("deal with id " + item.getId() + " has price " + item.getPrice() + " but requested " + requestDTO.getPrice());
            }

            if (requestDTO.getDealStatus().equals("sale")) {
                final int affected = template.update(
                        // language=PostgreSQL
                        """
                                UPDATE deals SET balance_btc = balance_btc - :balance_btc
                                WHERE id = :id
                                AND removed = FALSE
                                """,
                        Map.of(
                                "id", requestDTO.getId(),
                                "saleBtc", requestDTO.getBalance_btc()
                        )
                );
                if (affected == 0) {
                    throw new DealRegistrationException("can't update balance_btc for deal with id " + requestDTO.getId());
                }
            }

            if (requestDTO.getDealStatus().equals("buy")) {
                final int affected = template.update(
                        // language=PostgreSQL
                        """
                                UPDATE deals SET balance_btc = balance_btc = balance_btc + :balance_btc
                                WHERE id = :id 
                                AND removed = FALSE
                                """,
                        Map.of("id", requestDTO.getId(),
                                "saleBtc", requestDTO.getBalance_btc()
                        )
                );
                if (affected == 0) {
                    throw new DealRegistrationException("can't update balance with value " + requestDTO.getBalance_btc() + "for deal with id" + requestDTO.getId());
                }
            }

            final DealModel deal = template.queryForObject(
                    // language=PostgreSQL
                    """
                            INSERT INTO deals (id, salesman, price, balance_btc, balance_rub, dealStatus) 
                            VALUES (:id, :salesman, :price, :balance_btc, :balance_rub, :dealStatus)
                            RETURNING id, salesman, price, balance_btc, balance_rub, dealstatus
                            """,
                    Map.of("id", requestDTO.getId(),
                            "salesman", item.getSalesman(),
                            "price", item.getPrice(),
                            "balance_btc", item.getBalance_btc(),
                            "balance_rub", item.getBalance_rub(),
                            "dealStatus", item.getDealStatus()
                    ), dealRowMapper

            );

            final DealRegisterResponseDTO responseDTO = new DealRegisterResponseDTO(new DealRegisterResponseDTO.Deal(
                    deal.getId(),
                    deal.getSalesman(),
                    deal.getPrice(),
                    deal.getBalance_btc(),
                    deal.getBalance_rub(),
                    deal.getDealStatus()
            ));

            return responseDTO;

        } catch (EmptyResultDataAccessException e) {
            throw new OrderNotFoundException(e);
        }
    }
}