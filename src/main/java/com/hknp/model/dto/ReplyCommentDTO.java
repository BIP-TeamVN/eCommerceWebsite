package com.hknp.model.dto;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ReplyCommentDTO {
   Long subCommentId;
   Long rateCommentId;
   String comment;
   Integer noOfLike;
   Integer noOfDislike;
   Long userId;

   public ReplyCommentDTO (ResultSet resultSet) throws SQLException {
      subCommentId = resultSet.getLong("SUB_COMMENT_ID");
      rateCommentId = resultSet.getLong("RATE_COMMENT_ID");
      comment = resultSet.getString("COMMENT");
      noOfLike = resultSet.getInt("NO_OF_LIKE");
      noOfDislike = resultSet.getInt("NO_OF_DISLIKE");
      userId = resultSet.getLong("USER_ID");
   }
}
