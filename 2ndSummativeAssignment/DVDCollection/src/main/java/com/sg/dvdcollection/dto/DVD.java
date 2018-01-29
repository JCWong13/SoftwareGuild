/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.dvdcollection.dto;

/**
 *
 * @author janie
 */
public class DVD {
    
    private String dvdTitle;
    private String releaseDate;
    private String mpaaRating;
    private String directorName;
    private String studio;
    private String userNote;

    public DVD(String dvdTitle) {
        this.dvdTitle = dvdTitle;
    }

    public String getDVDTitle() {
        return dvdTitle;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getMpaaRating() {
        return mpaaRating;
    }

    public void setMpaaRating(String mpaaRating) {
        this.mpaaRating = mpaaRating;
    }

    public String getDirectorName() {
        return directorName;
    }

    public void setDirectorName(String directorName) {
        this.directorName = directorName;
    }

    public String getStudio() {
        return studio;
    }

    public void setStudio(String studio) {
        this.studio = studio;
    }

    public String getUserNote() {
        return userNote;
    }

    public void setUserNote(String userRatingNote) {
        this.userNote = userRatingNote;
    }
    
}
