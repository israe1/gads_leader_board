package com.israel.gadstoplearners.models;

public class Leader implements Comparable<Leader>{
    private int hours;
    private int score;
    private String name;
    private String country;
    private String badgeUrl;

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getBadgeUrl() {
        return badgeUrl;
    }

    public void setBadgeUrl(String badgeUrl) {
        this.badgeUrl = badgeUrl;
    }

    @Override
    public String toString() {
        return "Learner{" +
                "hours=" + hours +
                ", score='" + score + '\'' +
                ", name='" + name + '\'' +
                ", country='" + country + '\'' +
                ", badgeUrl='" + badgeUrl + '\'' +
                '}';
    }


    @Override
    public int compareTo(Leader learner) {
        if (hours > 0){
            if (hours < learner.getHours()){
                return 1;
            }else if(hours > learner.getHours()){
                return -1;
            }
        }else if(score > 0){
            if (score < learner.getScore()){
                return 1;
            }else if(score > learner.getScore()){
                return -1;
            }
        }
        return 0;
    }
}
