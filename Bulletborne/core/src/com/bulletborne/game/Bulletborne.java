package com.bulletborne.game;

import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.assets.AssetManager;
import com.bulletborne.game.view.MainMenuView;
import com.bulletborne.game.view.View;

public class Bulletborne extends Game {
	private SpriteBatch batch;
	private AssetManager assetManager;
	private Music music;
	private AndroidSaving preferences;
	private float musicVolume;

	/**
	 * Sets the preferences (save and load functions)
	 * @param preferences
	 */
	public Bulletborne(AndroidSaving preferences) {
		this.preferences = preferences;
	}

	/**
	 * Creates the game. Initializes the sprite batch and asset manager.
	 * Also starts the game until we have a main menu.
	 */
	@Override
	public void create () {
		batch = new SpriteBatch();
		assetManager = new AssetManager();

		musicVolume=preferences.loadMusicVolume();
		View.setAudioChanger(preferences.loadSoundChanger());
		View.setShipNumber(preferences.loadShipNumber());
		View.setBestScore(preferences.loadBestScore());

		music = Gdx.audio.newMusic(Gdx.files.internal("Skyrim8bitfinal_start.wav"));

        music.play();
		startGame();
	}

	/**
	 * Starts the game
	 */
	private void startGame() {
		setScreen(new MainMenuView(this));
	}

	/**
	 * Disposes of all assets.
	 */
	@Override
	public void dispose () {
		batch.dispose();
		assetManager.dispose();
		music.dispose();
	}

	/**
	 * Returns the asset manager used to load all textures and sounds.
	 *
	 * @return the asset manager
	 */
	public AssetManager getAssetManager() {
		return assetManager;
	}

	/**
	 * Returns the sprite batch used to improve drawing performance.
	 *
	 * @return the sprite batch
	 */
	public SpriteBatch getBatch() {
		return batch;
	}

	/**
	 * Returns the sprite batch used to improve drawing performance.
	 *
	 * @return the sprite batch
	 */
	public Music getMusic() { return music; }

	/**
	 * sets playing a music
	 * @param musicName
	 */
    public void setPlaying(String musicName){
        music = Gdx.audio.newMusic(Gdx.files.internal(musicName));
		music.setVolume(musicVolume);
		music.play();
    }

	/**
	 * Sets the volume of the music currently playing
	 */
	public void setVolume(){
		music.setVolume(musicVolume);
    }

	/**
	 * gets the MusicVolume
	 * @return musicVolume
	 */
	public float getMusicVolume() {
		return musicVolume;
	}

	/**
	 * Sets the music Volume to a given value
	 * @param musicVolume
	 */
	public void setMusicVolume(float musicVolume) {
		this.musicVolume = musicVolume;
	}

	/**
	 * Sabes the highscore and user preferences
	 * @param bestScore
	 * @param shipNumber
	 * @param audioChanger
	 */
	public void save(int bestScore, int shipNumber, float audioChanger) {
		preferences.saveBestScore(bestScore);
		preferences.saveShipNumber(shipNumber);
		preferences.saveSoundChanger(audioChanger);
		preferences.saveMusicVolume(musicVolume);
	}
}
