package org.example.manager;

import lombok.RequiredArgsConstructor;
import org.example.dto.OrderGetAllResponseDTO;
import org.example.dto.OrderGetByIdResponseDTO;
import org.example.dto.OrderSaveRequestDTO;
import org.example.dto.OrderSaveResponseDTO;
import org.example.exception.OrderNotFoundException;
import org.example.model.OrderModel;
import org.example.rowmapper.OrderRowMapper;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class OrderManager {
    private final NamedParameterJdbcTemplate template;
    private final OrderRowMapper orderRowMapper;
    private final String defaultImage = "image.jpeg";

    public OrderGetAllResponseDTO getAll() {
        final List<OrderModel> items = template.query(
                // language=PostgreSQL
                """
                        SELECT id, salesman, price, bitcoin, review, min_amount, max_amount, successful_deals, status, proStatus FROM orders
                        WHERE removed = FALSE
                        ORDER BY id
                        LIMIT 100
                        """, orderRowMapper);

        final OrderGetAllResponseDTO responseDTO = new OrderGetAllResponseDTO(new ArrayList<>(items.size()));
        for (OrderModel item : items) {
            responseDTO.getOrders().add(new OrderGetAllResponseDTO.Order(
                    item.getId(),
                    item.getSalesman(),
                    item.getPrice(),
                    item.getBitcoin(),
                    item.getReview(),
                    item.getMin_amount(),
                    item.getMax_amount(),
                    item.getSuccessful_deals(),
                    item.getStatus(),
                    item.getProStatus()
            ));
        }

        return responseDTO;
    }

    public OrderGetByIdResponseDTO getById(long id) {
        try {
            final OrderModel item = template.queryForObject(
                    // language=PostgreSQL
                    """
                            SELECT id, salesman, price, bitcoin, review, min_amount, max_amount, successful_deals, status, proStatus FROM orders
                            WHERE id = :id AND removed = FALSE
                            """,
                    Map.of("id", id),
                    orderRowMapper
            );

            final OrderGetByIdResponseDTO responseDTO = new OrderGetByIdResponseDTO(new OrderGetByIdResponseDTO.Order(
                    item.getId(),
                    item.getSalesman(),
                    item.getPrice(),
                    item.getBitcoin(),
                    item.getReview(),
                    item.getMin_amount(),
                    item.getMax_amount(),
                    item.getSuccessful_deals(),
                    item.getStatus(),
                    item.getProStatus()
            ));
            return responseDTO;
        } catch (EmptyResultDataAccessException e) {
            throw new OrderNotFoundException(e);
        }
    }


    public OrderSaveResponseDTO save(OrderSaveRequestDTO requestDTO) {
        if (requestDTO.getId() == 0) {
            return create(requestDTO);
        }
        return update(requestDTO);
    }

    private OrderSaveResponseDTO create(OrderSaveRequestDTO requestDTO) {
        final OrderModel item = template.queryForObject(
                // language=PostgreSQL
                """
                        INSERT INTO orders(salesman, price, bitcoin, review, min_amount, max_amount, successful_deals, status, proStatus) VALUES (:salesman, :price, :bitcoin, :review, :min_amount, :max_amount, :successful_deals, :status, :proStatus)
                        RETURNING id, salesman, price, bitcoin, review, min_amount, max_amount, successful_deals, status, proStatus
                        """,
                Map.of(
                        "salesman", requestDTO.getSalesman(),
                        "price", requestDTO.getPrice(),
                        "bitcoin", requestDTO.getBitcoin(),
                        "review", requestDTO.getReview(),
                        "min_amount", requestDTO.getMin_amount(),
                        "max_amount", requestDTO.getMax_amount(),
                        "successful_deals", requestDTO.getSuccessful_deals(),
                        "status", requestDTO.getStatus(),
                        "proStatus", getProStatus(requestDTO.getProStatus())
                ),
                orderRowMapper
        );

        final OrderSaveResponseDTO responseDTO = new OrderSaveResponseDTO(new OrderSaveResponseDTO.Order(
                item.getId(),
                item.getSalesman(),
                item.getPrice(),
                item.getBitcoin(),
                item.getReview(),
                item.getMin_amount(),
                item.getMax_amount(),
                item.getSuccessful_deals(),
                item.getStatus(),
                item.getProStatus()
        ));

        return responseDTO;
    }

    private OrderSaveResponseDTO update(OrderSaveRequestDTO requestDTO) {
        try {
            final OrderModel item = template.queryForObject(
                    // language=PostgreSQL
                    """
                            UPDATE orders SET  salesman = :salesman, price = :price, bitcoin = :bitcoin, review = :review, min_amount = :min_amount, max_amount = :max_amount, successful_deals = :successful_deals, status = :status, prostatus = :proStatus
                            WHERE id = :id AND removed = FALSE
                            RETURNING id, salesman, price, bitcoin, review, min_amount, max_amount, successful_deals, status, proStatus
                            """,
                    Map.of(
                            "id", requestDTO.getId(),
                            "salesman", requestDTO.getSalesman(),
                            "price", requestDTO.getPrice(),
                            "bitcoin", requestDTO.getBitcoin(),
                            "review", requestDTO.getReview(),
                            "min_amount", requestDTO.getMin_amount(),
                            "max_amount", requestDTO.getMax_amount(),
                            "successful_deals", requestDTO.getSuccessful_deals(),
                            "status", requestDTO.getStatus(),
                            "proStatus", getProStatus(requestDTO.getProStatus())
                    ),
                    orderRowMapper
            );

            final OrderSaveResponseDTO responseDTO = new OrderSaveResponseDTO(new OrderSaveResponseDTO.Order(
                    item.getId(),
                    item.getSalesman(),
                    item.getPrice(),
                    item.getBitcoin(),
                    item.getReview(),
                    item.getMin_amount(),
                    item.getMax_amount(),
                    item.getSuccessful_deals(),
                    item.getStatus(),
                    item.getProStatus()
            ));

            return responseDTO;
        } catch (EmptyResultDataAccessException e) {
            throw new OrderNotFoundException(e);
        }
    }

    public void removeById(long id) {
        int affected = template.update(
                // language=PostgreSQL
                """
                        UPDATE orders SET removed = TRUE WHERE id = :id
                        """,
                Map.of("id", id)
        );
        if (affected == 0) {
            throw new OrderNotFoundException("order with id" + id + " not found");
        }
    }

    private String getProStatus(String proStatus) {
        return proStatus == null ? defaultImage : proStatus;
    }
}
