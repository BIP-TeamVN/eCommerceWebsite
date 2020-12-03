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

    public ReplyCommentDTO (ResultSet resultSet) {
        try {
            subCommentId = resultSet.getLong("SUB_COMMENT_ID");
            rateCommentId = resultSet.getLong("RATE_COMMENT_ID");
            comment = resultSet.getString("COMMENT");
            noOfLike = resultSet.getInt("NO_OF_LIKE");
            noOfDislike = resultSet.getInt("NO_OF_DISLIKE");
            userId = resultSet.getLong("USER_ID");
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    public ReplyCommentDTO(Long subCommentId, Long rateCommentId, String comment, Integer noOfLike, Integer noOfDislike, Long userId) {
        this.subCommentId = subCommentId;
        this.rateCommentId = rateCommentId;
        this.comment = comment;
        this.noOfLike = noOfLike;
        this.noOfDislike = noOfDislike;
        this.userId = userId;
    }

    public Long getSubCommentId() {
        return subCommentId;
    }

    public void setSubCommentId(Long subCommentId) {
        this.subCommentId = subCommentId;
    }

    public Long getRateCommentId() {
        return rateCommentId;
    }

    public void setRateCommentId(Long rateCommentId) {
        this.rateCommentId = rateCommentId;
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
}
