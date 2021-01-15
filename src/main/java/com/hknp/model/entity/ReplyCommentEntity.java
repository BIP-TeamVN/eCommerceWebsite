package com.hknp.model.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "REPLY_COMMENT")
public class ReplyCommentEntity implements Serializable {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "SUB_COMMENT_ID")
   Long subCommentId;

   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "RATE_COMMENT_ID")
   RateCommentEntity rateCommentEntity;

   @Column(name = "COMMENT")
   String comment;

   @Column(name = "NO_OF_LIKE")
   Integer noOfLike;

   @Column(name = "NO_OF_DISLIKE")
   Integer noOfDislike;

   @OneToOne(fetch = FetchType.LAZY)
   @PrimaryKeyJoinColumn(name = "USER_ID")
   UserEntity userEntity;

   public ReplyCommentEntity() {
   }

   public Long getSubCommentId() {
      return subCommentId;
   }

   public void setSubCommentId(Long subCommentId) {
      this.subCommentId = subCommentId;
   }

   public RateCommentEntity getRateCommentEntity() {
      return rateCommentEntity;
   }

   public void setRateCommentEntity(RateCommentEntity rateCommentEntity) {
      this.rateCommentEntity = rateCommentEntity;
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

   public UserEntity getUserEntity() {
      return userEntity;
   }

   public void setUserEntity(UserEntity userEntity) {
      this.userEntity = userEntity;
   }
}
