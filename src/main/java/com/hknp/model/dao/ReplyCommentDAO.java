package com.hknp.model.dao;

import com.hknp.interfaces.IDataGet;
import com.hknp.interfaces.IDataUpdateAutoIncrement;
import com.hknp.model.dto.ReplyCommentDTO;
import com.hknp.utils.DatabaseUtils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ReplyCommentDAO implements IDataGet<Long, ReplyCommentDTO>, IDataUpdateAutoIncrement<Long, ReplyCommentDTO> {
   private static ReplyCommentDAO instance = null;

   private ReplyCommentDAO() {
   }

   public static ReplyCommentDAO getInstance() {
      if (instance == null) {
         instance = new ReplyCommentDAO();
      }
      return instance;
   }

   @Override
   public ArrayList<ReplyCommentDTO> gets() {
      ArrayList<ReplyCommentDTO> result = new ArrayList<>();

      String query = "SELECT * FROM REPLY_COMMENT;";
      ResultSet resultSet = DatabaseUtils.executeQuery(query, null);

      if (resultSet == null) {
         return result;
      }

      try {
         while (resultSet.next()) {
            ReplyCommentDTO replyCommentDTO = new ReplyCommentDTO(resultSet);
            result.add(replyCommentDTO);
         }
      } catch (SQLException exception) {
         exception.printStackTrace();
      }

      return result;
   }

   @Override
   public ReplyCommentDTO getById(Long id) {
      String query = "SELECT * FROM REPLY_COMMENT WHERE SUB_COMMENT_ID = " + id + ";";
      ResultSet resultSet = DatabaseUtils.executeQuery(query, null);

      try {
         if (resultSet != null && resultSet.next()) {
            return new ReplyCommentDTO(resultSet);
         }
      } catch (SQLException exception) {
         exception.printStackTrace();
      }
      return null;
   }

   @Override
   public Long insert(ReplyCommentDTO dto) {
      String sql = "INSERT INTO REPLY_COMMENT(SUB_COMMENT_ID, RATE_COMMENT_ID, COMMENT, NO_OF_LIKE, NO_OF_DISLIKE, USER_ID) " +
              "VALUES (?, ?, ?, ?, ?, ?);";
      List<Object> parameters = Arrays.asList(
              dto.getSubCommentId(),
              dto.getRateCommentId(),
              dto.getComment(),
              dto.getNoOfLike(),
              dto.getNoOfDislike(),
              dto.getUserId()
      );
      return (Long) DatabaseUtils.executeUpdateAutoIncrement(sql, parameters);
   }

   @Override
   public int update(ReplyCommentDTO dto) {
      String sql = "UPDATE REPLY_COMMENT SET RATE_COMMENT_ID = ?, COMMENT = ?, NO_OF_LIKE = ?, NO_OF_DISLIKE = ?, USER_ID = ? WHERE SUB_COMMENT_ID = ?";
      List<Object> parameters = Arrays.asList(
              dto.getRateCommentId(),
              dto.getComment(),
              dto.getNoOfLike(),
              dto.getNoOfDislike(),
              dto.getUserId(),
              dto.getSubCommentId()
      );
      return DatabaseUtils.executeUpdate(sql, parameters);
   }

   @Override
   public int delete(Long id) {
      String sql = "DELETE FROM REPLY_COMMENT WHERE SUB_COMMENT_ID = ?";
      List<Object> parameters = Collections.singletonList(id);
      return DatabaseUtils.executeUpdate(sql, parameters);
   }
}
