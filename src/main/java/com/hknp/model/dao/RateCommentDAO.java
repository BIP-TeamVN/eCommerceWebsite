package com.hknp.model.dao;

import com.hknp.interfaces.IDataGet;
import com.hknp.interfaces.IDataUpdateAutoIncrement;
import com.hknp.model.dto.RateCommentDTO;
import com.hknp.utils.DatabaseUtils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class RateCommentDAO implements IDataGet<Long, RateCommentDTO>, IDataUpdateAutoIncrement<Long, RateCommentDTO> {
   private static RateCommentDAO instance = null;

   private RateCommentDAO() {
   }

   public static RateCommentDAO getInstance() {
      if (instance == null) {
         instance = new RateCommentDAO();
      }
      return instance;
   }

   @Override
   public ArrayList<RateCommentDTO> gets() {
      ArrayList<RateCommentDTO> result = new ArrayList<>();

      String query = "SELECT * FROM RATE_COMMENT;";
      ResultSet resultSet = DatabaseUtils.executeQuery(query, null);

      if (resultSet == null) {
         return result;
      }

      try {
         while (resultSet.next()) {
            RateCommentDTO rateCommentDTO = new RateCommentDTO(resultSet);
            result.add(rateCommentDTO);
         }
      } catch (SQLException exception) {
         exception.printStackTrace();
      }

      return result;
   }

   @Override
   public RateCommentDTO getById(Long id) {
      String query = "SELECT * FROM RATE_COMMENT WHERE RATE_COMMENT_ID = " + id + ";";
      ResultSet resultSet = DatabaseUtils.executeQuery(query, null);

      try {
         if (resultSet != null && resultSet.next()) {
            return new RateCommentDTO(resultSet);
         }
      } catch (SQLException exception) {
         exception.printStackTrace();
      }
      return null;
   }

   @Override
   public Long insert(RateCommentDTO dto) {
      String sql = "INSERT INTO PROVINCE(RATE_START, COMMENT, NO_OF_LIKE, NO_OF_DISLIKE, USER_ID, PRODUCT_ID) " +
              "VALUES (?, ?, ?, ?, ?, ?);";
      List<Object> parameters = Arrays.asList(
              dto.getRateStart(),
              dto.getComment(),
              dto.getNoOfLike(),
              dto.getNoOfDislike(),
              dto.getUserId(),
              dto.getProductId()
      );
      return (Long) DatabaseUtils.executeUpdateAutoIncrement(sql, parameters);
   }

   @Override
   public int update(RateCommentDTO dto) {
      String sql = "UPDATE RATE_COMMENT SET RATE_START = ?, COMMENT = ?, NO_OF_LIKE = ?, NO_OF_DISLIKE = ?, USER_ID = ?, PRODUCT_ID = ? WHERE RATE_COMMENT_ID = ?";
      List<Object> parameters = Arrays.asList(
              dto.getRateStart(),
              dto.getComment(),
              dto.getNoOfLike(),
              dto.getNoOfDislike(),
              dto.getUserId(),
              dto.getProductId(),
              dto.getRateCommentId()
      );
      return DatabaseUtils.executeUpdate(sql, parameters);
   }

   @Override
   public int delete(Long id) {
      String sql = "DELETE FROM RATE_COMMENT WHERE RATE_COMMENT_ID = ?";
      List<Object> parameters = Collections.singletonList(id);
      return DatabaseUtils.executeUpdate(sql, parameters);
   }
}
