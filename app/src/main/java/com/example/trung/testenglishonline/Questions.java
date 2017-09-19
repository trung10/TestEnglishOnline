package com.example.trung.testenglishonline;

import java.util.ArrayList;

/**
 * Created by trung on 9/13/17.
 */

public class Questions {
    private int ID;
    private String QUESTION;
    private String OPTA;
    private String OPTB;
    private String OPTC;
    private String ANSWER;

    @Override
    public String toString() {
        return "Questions{" +
                "ID=" + ID +
                ", QUESTION='" + QUESTION + '\'' +
                ", OPTA='" + OPTA + '\'' +
                ", OPTB='" + OPTB + '\'' +
                ", OPTC='" + OPTC + '\'' +
                ", ANSWER='" + ANSWER + '\'' +
                '}';
    }

    public Questions(int ID, String QUESTION, String OPTA, String OPTB, String OPTC, String ANSWER) {
        this.ID = ID;
        this.QUESTION = QUESTION;
        this.OPTA = OPTA;
        this.OPTB = OPTB;
        this.OPTC = OPTC;
        this.ANSWER = ANSWER;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getQUESTION() {
        return QUESTION;
    }

    public void setQUESTION(String QUESTION) {
        this.QUESTION = QUESTION;
    }

    public String getOPTA() {
        return OPTA;
    }

    public void setOPTA(String OPTA) {
        this.OPTA = OPTA;
    }

    public String getOPTB() {
        return OPTB;
    }

    public void setOPTB(String OPTB) {
        this.OPTB = OPTB;
    }

    public String getOPTC() {
        return OPTC;
    }

    public void setOPTC(String OPTC) {
        this.OPTC = OPTC;
    }

    public String getANSWER() {
        return ANSWER;
    }

    public void setANSWER(String ANSWER) {
        this.ANSWER = ANSWER;
    }
}
