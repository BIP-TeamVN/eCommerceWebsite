package com.hknp.model.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "RATE_COMMENT")
public class RateCommentEntity implements Serializable {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "RATE_COMMENT_ID")
   Long rateCommentId;

   @Column(name = "RATE_START")
   Integer rateStart;

   @Column(name = "COMMENT")
   String comment;

   @Column(name = "NO_OF_LIKE")
   Integer noOfLike;

   @Column(name = "NO_OF_DISLIKE")
   Integer noOfDislike;

   @OneToOne(fetch = FetchType.LAZY)
   @PrimaryKeyJoinColumn(name = "USER_ID")
   UserEntity userEntity;

   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "PRODUCT_ID")
   ProductEntity productEntity;

   @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
   @JoinColumn(name = "RATE_COMMENT_ID")
   List<ReplyCommentEntity> replyCommentEntities;

   public RateCommentEntity() {
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

   public UserEntity getUserEntity() {
      return userEntity;
   }

   public void setUserEntity(UserEntity userEntity) {
      this.userEntity = userEntity;
   }

   public ProductEntity getProductEntity() {
      return productEntity;
   }

   public void setProductEntity(ProductEntity productEntity) {
      this.productEntity = productEntity;
   }
}
