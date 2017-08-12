package com.codeside.pvapp.service.dto;

import java.io.Serializable;

/**
 * A DTO for Generic SurveyQuestion.
 */
public class SurveyQuestionDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    private String id;
    private String question;
    private String idiom;
    private SurveyQuestionDTO nextTrueQuestion;
    private SurveyQuestionDTO nextFalseQuestion;
    private String linkedAttribute;
    
    public String getId() {
  		return id;
  	}

  	public void setId(String id) {
  		this.id = id;
  	}

    public String getQuestion() {
  		return question;
  	}

    public SurveyQuestionDTO question(String question) {
      this.question = question;
      return this;
    }

  	public void setQuestion(String question) {
  		this.question = question;
  	}

  	public SurveyQuestionDTO getNextTrueQuestion() {
  		return nextTrueQuestion;
  	}

  	public SurveyQuestionDTO nextTrueQuestion(SurveyQuestionDTO nextTrueQuestion) {
      this.nextTrueQuestion = nextTrueQuestion;
      return this;
    }

  	public void setNextTrueQuestion(SurveyQuestionDTO nextTrueQuestion) {
  		this.nextTrueQuestion = nextTrueQuestion;
  	}

  	public SurveyQuestionDTO getNextFalseQuestion() {
  		return nextFalseQuestion;
  	}

  	public SurveyQuestionDTO nextFalseQuestion(SurveyQuestionDTO nextFalseQuestion) {
      this.nextFalseQuestion = nextFalseQuestion;
      return this;
    }

  	public void setNextFalseQuestion(SurveyQuestionDTO nextFalseQuestion) {
  		this.nextFalseQuestion = nextFalseQuestion;
  	}

  	public String getLinkedAttribute() {
  		return linkedAttribute;
  	}

  	public SurveyQuestionDTO linkedAttribute(String linkedAttribute) {
        this.linkedAttribute = linkedAttribute;
        return this;
    }

  	public void setLinkedAttribute(String linkedAttribute) {
  		this.linkedAttribute = linkedAttribute;
  	}

	   public String getIdiom() {
        return idiom;
    }

    public SurveyQuestionDTO idiom(String idiom) {
        this.idiom = idiom;
        return this;
    }

    public void setIdiom(String idiom) {
        this.idiom = idiom;
    }

    @Override
    public String toString() {
        return "SurveyQuestionDTO{" +
    		", id='" + getId() + "'" +
            ", question='" + getQuestion() + "'" +
            ", nextTrueQuestion='" + getNextTrueQuestion() + "'" +
            ", nextFalseQuestion='" + getNextFalseQuestion() + "'" +
            ", linkedAttribute='" + getLinkedAttribute() + "'" +
            ", idiom='" + getIdiom() + "'" +
            "}";
    }
}
