package si.strimr.track.metadata.models.dtos;

import javax.persistence.*;

public class TrackMetadata {

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTrackName() {
        return trackName;
    }

    public void setTrackName(String trackName) {
        this.trackName = trackName;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getDeezerTrackId() {
        return deezerTrackId;
    }

    public void setDeezerTrackId(String deezerTrackId) {
        this.deezerTrackId = deezerTrackId;
    }

    public String getPreview() {
        return preview;
    }

    public void setPreview(String preview) {
        this.preview = preview;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public String getAlbumPicture() {
        return albumPicture;
    }

    public void setAlbumPicture(String albumPicture) {
        this.albumPicture = albumPicture;
    }

    private Integer id;

    private String trackName;

    private String authorName;

    private String tags;

    private String deezerTrackId;

    private String preview;

    private Integer duration;

    private String albumPicture;
}