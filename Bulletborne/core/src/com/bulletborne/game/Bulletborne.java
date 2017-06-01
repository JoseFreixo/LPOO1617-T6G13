package com.bulletborne.game;

import com.badlogic.gdx.audio.Music;
import com.bulletborne.game.view.GameView;
import com.bulletborne.game.model.GameModel;
import com.bulletborne.game.controller.GameController;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.assets.AssetManager;
import com.bulletborne.game.view.MainMenuView;

public class Bulletborne extends Game {
	private SpriteBatch batch;
	private AssetManager assetManager;
	private Music music;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		assetManager = new AssetManager();
		music = Gdx.audio.newMusic(Gdx.files.internal("Skyrim8bitfinal_start.wav"));
		music.setVolume(1f);
        music.play();
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

    public void setPlaying(String musicName){
        music = Gdx.audio.newMusic(Gdx.files.internal(musicName));
		music.play();
    }
}
