package com.hknp.model.dto;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RateCommentDTO {
   Long rateCommentId;
   Integer rateStart;
   String comment;
   Integer noOfLike;
   Integer noOfDislike;
   Long userId;
   Long productId;

   public RateCommentDTO (ResultSet resultSet) throws SQLException {
      rateCommentId = resultSet.getLong("RATE_COMMENT_ID");
      rateStart = resultSet.getInt("RATE_START");
      comment = resultSet.getString("COMMENT");
      noOfLike = resultSet.getInt("NO_OF_LIKE");
      noOfDislike = resultSet.getInt("NO_OF_DISLIKE");
      userId = resultSet.getLong("USER_ID");
      productId = resultSet.getLong("PRODUCT_ID");
   }
}
