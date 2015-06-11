package com.tmount.business.voice.bean;

public class MusicVoiceMain {
	String songid = "";
	String songname = "";
	String artistname = "";
	String albumName = "";
	String songPicSmall = "";

	public String getSongid() {
		return songid;
	}

	public void setSongid(String songid) {
		this.songid = songid;
	}

	public String getSongname() {
		return songname;
	}

	public void setSongname(String songname) {
		this.songname = songname;
	}

	public String getArtistname() {
		return artistname;
	}

	public void setArtistname(String artistname) {
		this.artistname = artistname;
	}

	public String getAlbumName() {
		return albumName;
	}

	public void setAlbumName(String albumName) {
		this.albumName = albumName;
	}

	public String getSongPicSmall() {
		return songPicSmall;
	}

	public void setSongPicSmall(String songPicSmall) {
		this.songPicSmall = songPicSmall;
	}

	public String getLrcLink() {
		return lrcLink;
	}

	public void setLrcLink(String lrcLink) {
		this.lrcLink = lrcLink;
	}

	public String getSongLink() {
		return songLink;
	}

	public void setSongLink(String songLink) {
		this.songLink = songLink;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	String lrcLink = "";
	String songLink = "";
	String time = "";
	String size = "";
}
