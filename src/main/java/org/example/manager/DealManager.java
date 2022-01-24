package org.example.manager;


import lombok.RequiredArgsConstructor;
import org.example.dto.DealRegisterRequestDTO;
import org.example.dto.DealRegisterResponseDTO;
import org.example.model.DealModel;
import org.example.rowmapper.DealRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@RequiredArgsConstructor
public class DealManager {
    private final NamedParameterJdbcTemplate template;
    private final DealRowMapper dealRowMapper;

    public DealRegisterResponseDTO register(DealRegisterRequestDTO requestDTO) {
            if (requestDTO.getId() == 0) {
                final DealModel item = template.queryForObject(
                        // language=PostgreSQL
                        """
                                INSERT INTO deals (id, name, price, balance_btc, balance_rub, dealStatus)
                                VALUES (:id, :name, :price, :balance_btc, :balance_rub, :dealStatus)
                                RETURNING id, name, price, balance_btc, balance_rub, dealStatus
                                """,
                        Map.of("id", requestDTO.getId(),
                                "name", requestDTO.getName(),
                                "price", requestDTO.getPrice(),
                                "balance_btc", requestDTO.getBalance_btc(),
                                "balance_rub", requestDTO.getBalance_rub(),
                                "dealStatus", requestDTO.getDealStatus()), dealRowMapper
                );

                final DealRegisterResponseDTO responseDTO = new DealRegisterResponseDTO(new DealRegisterResponseDTO.Deal(
                        item.getId(),
                        item.getName(),
                        item.getPrice(),
                        item.getBalance_btc(),
                        item.getBalance_rub(),
                        item.getDealStatus()
                ));

                return responseDTO;

            }
        return null;
    }
}
//
//    @Transactional
//    public DealRegisterResponseDTO register(DealRegisterRequestDTO requestDTO) {
//        try {
//            final DealModel item = template.queryForObject(
//                    // language=PostgreSQL
//                    """
//                            SELECT id, name, price, balance_btc, balance_rub
//                            FROM deals
//                            WHERE id = :id AND removed = FALSE
//                            """,
//                    Map.of("id", requestDTO.getId()),
//                    dealRowMapper
//            );
//
//            if (item.getBalance_btc() < requestDTO.getBalance_btc()) {
//                throw new DealBtcNotEnoughException("deal with id " + item.getId() + " has bitcoin " + item.getBalance_btc() + " but requested " + requestDTO.getBalance_btc());
//            }
//
//            if (item.getPrice() != requestDTO.getPrice()) {
//                throw new DealPriceChangedException("deal with id " + item.getId() + " has price " + item.getPrice() + " but requested " + requestDTO.getPrice());
//            }
//
//            if (requestDTO.getDealStatus().equals("sale")) {
//                final int affected = template.update(
//                        // language=PostgreSQL
//                        """
//                                UPDATE deals SET balance_btc = balance_btc - :saleBtc
//                                WHERE id = :dealId
//                                AND removed = FALSE
//                                """,
//                        Map.of(
//                                "dealId", requestDTO.getId(),
//                                "saleBtc", requestDTO.getBalance_btc()
//                        )
//                );
//                if (affected == 0) {
//                    throw new DealRegistrationException("can't update balance_btc for deal with id " + requestDTO.getId());
//                }
//            }
//            // TODO: btc -> rub (price - btc)
//            //       if (limit, текущий баланс)
//            //       register deals (id, amount, status - btc2rub | rub2btc (сравнение?)
//            //       user1: btc = 0,
//            //       rub += price * btc
//            //       user2: btc += 1, rub -= price * btc
//
//            if (requestDTO.getDealStatus().equals("buy")) {
//                final int affected = template.update(
//                        // language=PostgreSQL
//                        """
//                                UPDATE deals SET balance_btc = balance_btc + :balance_btc, balance_rub = balance_rub - (price * balance_btc)
//                                WHERE id = :id
//                                AND removed = FALSE
//                                """,
//                        Map.of("dealId", requestDTO.getId(),
//                                "saleBtc", requestDTO.getBalance_btc()
//                        )
//                );
//                if (affected == 0) {
//                    throw new DealRegistrationException("can't update balance with value " + requestDTO.getBalance_btc() + "for deal with id" + requestDTO.getId());
//                }
//            }
//
//            final DealModel deal = template.queryForObject(
//                    // language=PostgreSQL
//                    """
//                            INSERT INTO deals (coin_id, name, price, balance_btc, dealStatus)
//                            VALUES (:dealId, :salesman, :price, :balance_btc, :balance_rub, :dealStatus)
//                            RETURNING id, coin_id, name, price, balance_btc, dealstatus
//                            """,
//                    Map.of("deal_id", requestDTO.getId(),
//                            "salesman", item.getName(),
//                            "price", item.getPrice(),
//                            "balance_btc", item.getBalance_btc(),
//                            "dealStatus", item.getDealStatus()
//                    ), dealRowMapper
//
//            );
//
//            final DealRegisterResponseDTO responseDTO = new DealRegisterResponseDTO(new DealRegisterResponseDTO.Deal(
//                    deal.getId(),
//                    deal.getDealId(),
//                    deal.getName(),
//                    deal.getPrice(),
//                    deal.getBalance_btc(),
//                    deal.getDealStatus()
//            ));
//
//            return responseDTO;
//
//        } catch (EmptyResultDataAccessException e) {
//            throw new DealNotFoundException(e);
//        }
//    }
//}