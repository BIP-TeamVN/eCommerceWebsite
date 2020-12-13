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

   public RateCommentDTO(ResultSet resultSet) {
      try {
         rateCommentId = resultSet.getLong("RATE_COMMENT_ID");
         rateStart = resultSet.getInt("RATE_START");
         comment = resultSet.getString("COMMENT");
         noOfLike = resultSet.getInt("NO_OF_LIKE");
         noOfDislike = resultSet.getInt("NO_OF_DISLIKE");
         userId = resultSet.getLong("USER_ID");
         productId = resultSet.getLong("PRODUCT_ID");
      } catch (SQLException exception) {
         exception.printStackTrace();
      }
   }

   public RateCommentDTO(Long rateCommentId, Integer rateStart, String comment, Integer noOfLike, Integer noOfDislike, Long userId, Long productId) {
      this.rateCommentId = rateCommentId;
      this.rateStart = rateStart;
      this.comment = comment;
      this.noOfLike = noOfLike;
      this.noOfDislike = noOfDislike;
      this.userId = userId;
      this.productId = productId;
   }

   public Long getRateCommentId() {
      return rateCommentId;
   }

   public void setRateCommentId(Long rateCommentId) {
      this.rateCommentId = rateCommentId;
   }

   public Integer getRateStart() {
      return rateStart;
   }

   public void setRateStart(Integer rateStart) {
      this.rateStart = rateStart;
   }

   public String getComment() {
      return comment;
   }

   public void setComment(String comment) {
      this.comment = comment;
   }

   public Integer getNoOfLike() {
      return noOfLike;
   }

   public void setNoOfLike(Integer noOfLike) {
      this.noOfLike = noOfLike;
   }

   public Integer getNoOfDislike() {
      return noOfDislike;
   }

   public void setNoOfDislike(Integer noOfDislike) {
      this.noOfDislike = noOfDislike;
   }

   public Long getUserId() {
      return userId;
   }

   public void setUserId(Long userId) {
      this.userId = userId;
   }

   public Long getProductId() {
      return productId;
   }

   public void setProductId(Long productId) {
      this.productId = productId;
   }
}
