package com.example.aquaanalyzomatic;
import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.Map;

public class matchData {
    private String uid, currentDate;

    private int teamNum, matchNum, teleL1Num, teleL2Num, teleL3Num, teleL4Num, autonL1Num, autonL2Num, autonL3Num, autonL4Num,
            autonNetAttemptsNum, autonNetScoredNum, teleNetAttemptsNum, teleNetScoredNum, teleProcessedNum, autonProcessedNum, HPScoredNum, HPShotsNum, checkHumanPlayer, checkParking, checkShallowClimb, checkDeepClimb, checkLeaveStart;


    public matchData(String uid, String currentDate, int teamNum, int matchNum, int teleL1Num, int teleL2Num, int teleL3Num, int teleL4Num, int autonL1Num, int autonL2Num, int autonL3Num, int autonL4Num, int autonNetAttemptsNum, int autonNetScoredNum, int teleNetAttemptsNum, int teleNetScoredNum, int teleProcessedNum, int autonProcessedNum, int HPScoredNum, int HPShotsNum, int checkHumanPlayer, int checkParking, int checkShallowClimb, int checkDeepClimb, int checkLeaveStart) {
        this.uid = uid;
        this.currentDate = currentDate;
        this.teamNum = teamNum;
        this.matchNum = matchNum;
        this.teleL1Num = teleL1Num;
        this.teleL2Num = teleL2Num;
        this.teleL3Num = teleL3Num;
        this.teleL4Num = teleL4Num;
        this.autonL1Num = autonL1Num;
        this.autonL2Num = autonL2Num;
        this.autonL3Num = autonL3Num;
        this.autonL4Num = autonL4Num;
        this.autonNetAttemptsNum = autonNetAttemptsNum;
        this.autonNetScoredNum = autonNetScoredNum;
        this.teleNetAttemptsNum = teleNetAttemptsNum;
        this.teleNetScoredNum = teleNetScoredNum;
        this.teleProcessedNum = teleProcessedNum;
        this.autonProcessedNum = autonProcessedNum;
        this.HPScoredNum = HPScoredNum;
        this.HPShotsNum = HPShotsNum;
        this.checkHumanPlayer = checkHumanPlayer;
        this.checkParking = checkParking;
        this.checkShallowClimb = checkShallowClimb;
        this.checkDeepClimb = checkDeepClimb;
        this.checkLeaveStart = checkLeaveStart;
    }

    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("uid", uid);
        result.put("currentDate", currentDate);
        result.put("teamNum", teamNum);
        result.put("matchNum", matchNum);
        result.put("teleL1Num", teleL1Num);
        result.put("teleL2Num", teleL2Num);
        result.put("teleL3Num", teleL3Num);
        result.put("teleL4Num", teleL4Num);
        result.put("autonL1Num", autonL1Num);
        result.put("autonL2Num", autonL2Num);
        result.put("autonL3Num", autonL3Num);
        result.put("autonL4Num", autonL4Num);
        result.put("autonNetAttemptsNum", autonNetAttemptsNum);
        result.put("autonNetScoredNum", autonNetScoredNum);
        result.put("teleNetAttemptsNum", teleNetAttemptsNum);
        result.put("teleNetScoredNum", teleNetScoredNum);
        result.put("teleProcessedNum", teleProcessedNum);
        result.put("autonProcessedNum", autonProcessedNum);
        result.put("HPScoredNum", HPScoredNum);
        result.put("HPShotsNum", HPShotsNum);
        result.put("CheckHumanPlayer", checkHumanPlayer);
        result.put("CheckParking", checkParking);
        result.put("CheckShallowClimb", checkShallowClimb);
        result.put("CheckDeepClimb", checkDeepClimb);
        result.put("CheckLeaveStart", checkLeaveStart);

        return result;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public void setCurrentDate(String currentDate) {
        this.currentDate = currentDate;
    }

    public void setTeamNum(int teamNum) {
        this.teamNum = teamNum;
    }

    public void setMatchNum(int matchNum) {
        this.matchNum = matchNum;
    }

    public void setTeleL1Num(int teleL1Num) {
        this.teleL1Num = teleL1Num;
    }

    public void setTeleL2Num(int teleL2Num) {
        this.teleL2Num = teleL2Num;
    }

    public void setTeleL3Num(int teleL3Num) {
        this.teleL3Num = teleL3Num;
    }

    public void setTeleL4Num(int teleL4Num) {
        this.teleL4Num = teleL4Num;
    }

    public void setAutonL1Num(int autonL1Num) {
        this.autonL1Num = autonL1Num;
    }

    public void setAutonL2Num(int autonL2Num) {
        this.autonL2Num = autonL2Num;
    }

    public void setAutonL3Num(int autonL3Num) {
        this.autonL3Num = autonL3Num;
    }

    public void setAutonL4Num(int autonL4Num) {
        this.autonL4Num = autonL4Num;
    }

    public void setAutonNetAttemptsNum(int autonNetAttemptsNum) {
        this.autonNetAttemptsNum = autonNetAttemptsNum;
    }

    public void setAutonNetScoredNum(int autonNetScoredNum) {
        this.autonNetScoredNum = autonNetScoredNum;
    }

    public void setTeleNetAttemptsNum(int teleNetAttemptsNum) {
        this.teleNetAttemptsNum = teleNetAttemptsNum;
    }

    public void setTeleNetScoredNum(int teleNetScoredNum) {
        this.teleNetScoredNum = teleNetScoredNum;
    }

    public void setTeleProcessedNum(int teleProcessedNum) {
        this.teleProcessedNum = teleProcessedNum;
    }

    public void setAutonProcessedNum(int autonProcessedNum) {
        this.autonProcessedNum = autonProcessedNum;
    }

    public void setHPScoredNum(int HPScoredNum) {
        this.HPScoredNum = HPScoredNum;
    }

    public void setHPShotsNum(int HPShotsNum) {
        this.HPShotsNum = HPShotsNum;
    }

    public void setCheckHumanPlayer(int checkHumanPlayer) {
        checkHumanPlayer = checkHumanPlayer;
    }

    public void setCheckParking(int checkParking) {
        checkParking = checkParking;
    }

    public void setCheckShallowClimb(int checkShallowClimb) {
        checkShallowClimb = checkShallowClimb;
    }

    public void setCheckDeepClimb(int checkDeepClimb) {
        checkDeepClimb = checkDeepClimb;
    }

    public void setCheckLeaveStart(int checkLeaveStart) {
        checkLeaveStart = checkLeaveStart;
    }
}
