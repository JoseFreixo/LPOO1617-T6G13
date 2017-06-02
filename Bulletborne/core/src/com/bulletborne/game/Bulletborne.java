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

	public Bulletborne(AndroidSaving preferences) {
		this.preferences = preferences;
	}

	@Override
	public void create () {
		batch = new SpriteBatch();
		assetManager = new AssetManager();
		music = Gdx.audio.newMusic(Gdx.files.internal("Skyrim8bitfinal_start.wav"));
		music.setVolume(0.5f);
        music.play();
        View.setBestScore(preferences.loadBestScore());//TODO O RESTO DAS PREFERENCIAS
		startGame();
	}

	/**
	 * Starts the game
	 */
	private void startGame() {
		setScreen(new MainMenuView(this));
	}
	
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

    public AndroidSaving getPreferences() {
        return preferences;
    }

    public void setPlaying(String musicName, float volume){
        music = Gdx.audio.newMusic(Gdx.files.internal(musicName));
		music.setVolume(volume);

		music.play();
    }
    public void setVolume(float audio, float changer){
        music.setVolume(audio*changer);
    }

}
