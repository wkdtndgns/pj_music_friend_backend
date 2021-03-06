package net.kang.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.kang.domain.Music;
import net.kang.domain.StandardDeviation;
import net.kang.domain.User;
import net.kang.getModel.GuestMusicTableRow;
import net.kang.getModel.UserMusicTableRow;
import net.kang.repository.MusicRepository;
import net.kang.repository.StandardDeviationRepository;
import net.kang.repository.UserRepository;

@Service
public class MusicService {
	@Autowired UserRepository userRepository;
	@Autowired MusicRepository musicRepository;
	@Autowired StandardDeviationRepository standardDeviationRepository;

	public List<UserMusicTableRow> myPlayList(String userId){
		User user = userRepository.findByUserId(userId).orElse(new User());
		if(user.equals(new User())) return new ArrayList<UserMusicTableRow>();
		StandardDeviation sd=standardDeviationRepository.findTopByOrderByLatestDateDesc();
		List<Music> musicList=user.getPlayList();
		List<UserMusicTableRow> row = new ArrayList<UserMusicTableRow>();
		for(int k=0;k<musicList.size();k++) {
			UserMusicTableRow newRow=new UserMusicTableRow();
			Music music=musicList.get(k);
			newRow.setId(music.getId());
			if(user.getPlayList().contains(music)) {
				newRow.setLiked(true);
			}
			newRow.setRank(k+1);
			newRow.setRate((int)(music.getPopulate().getScore()-sd.getAverage()));
			newRow.setSinger(music.getSinger());
			newRow.setTitle(music.getTitle());
			newRow.setAlbum(music.getAlbum());
			row.add(newRow);
		}
		return row;
	}

	public List<GuestMusicTableRow> getGuestMusicTableRow(){
		List<GuestMusicTableRow> row = new ArrayList<GuestMusicTableRow>();
		List<Music> musicList=musicRepository.findByOrderByPopulateScoreDesc();
		StandardDeviation sd=standardDeviationRepository.findTopByOrderByLatestDateDesc();
		for(int k=0;k<musicList.size();k++) {
			GuestMusicTableRow newRow=new GuestMusicTableRow();
			Music music=musicList.get(k);
			newRow.setId(music.getId());
			newRow.setRank(k+1);
			newRow.setRate((int)(music.getPopulate().getScore()-sd.getAverage()));
			newRow.setSinger(music.getSinger());
			newRow.setTitle(music.getTitle());
			newRow.setAlbum(music.getAlbum());
			row.add(newRow);
		}
		return row;
	}

	public List<UserMusicTableRow> getUserMusicTableRow(String userId){
		User user = userRepository.findByUserId(userId).orElse(new User());
		if(user.equals(new User())) return new ArrayList<UserMusicTableRow>();
		List<UserMusicTableRow> row = new ArrayList<UserMusicTableRow>();
		List<Music> musicList=musicRepository.findByOrderByPopulateScoreDesc();
		StandardDeviation sd=standardDeviationRepository.findTopByOrderByLatestDateDesc();
		for(int k=0;k<musicList.size();k++) {
			UserMusicTableRow newRow=new UserMusicTableRow();
			Music music=musicList.get(k);
			newRow.setId(music.getId());
			if(user.getPlayList().contains(music)) {
				newRow.setLiked(true);
			}
			newRow.setRank(k+1);
			newRow.setRate((int)(music.getPopulate().getScore()-sd.getAverage()));
			newRow.setSinger(music.getSinger());
			newRow.setTitle(music.getTitle());
			newRow.setAlbum(music.getAlbum());
			row.add(newRow);
		}
		return row;
	}

	public List<Music> findAll(){
		return musicRepository.findAll();
	}

	public void insertMusicList(String userId, int musicId) {
		Optional<User> tmUser = userRepository.findByUserId(userId);
        User user=tmUser.orElse(new User());
        if (user.equals(new User())) return;
        List<Music> musicList = user.getPlayList();
        Music newMusic = musicRepository.findById(musicId).orElse(new Music());
        if(!newMusic.equals(new Music()))
        	musicList.add(newMusic);
        user.setPlayList(musicList);
        userRepository.save(user);
	}

	public void deleteMusicList(String userId, int musicId) {
		Optional<User> tmUser = userRepository.findByUserId(userId);
        User user=tmUser.orElse(new User());
        if (user.equals(new User())) return;
        List<Music> musicList = user.getPlayList();
        Music deleteMusic = musicRepository.findById(musicId).orElse(new Music());
        if(!deleteMusic.equals(new Music()))
        	musicList.remove(deleteMusic);
        user.setPlayList(musicList);
        userRepository.save(user);
	}
}
