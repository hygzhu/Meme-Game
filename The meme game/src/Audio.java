import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Audio implements Runnable {

	AudioInputStream audio;
	static Clip MemeCircus;

	public static void run(String path) {
		// play sound file

		if (path.equals("MemeCircus")) {
			if (MemeCircus.isRunning() == true) {
				closeAll();
			} else {
				closeAll();
				MemeCircus.start();
				MemeCircus.loop(100);
			}
		}
	

	}


	public static void initialize() {
		// Finding sound file

		try {
			// initializes all audio clips
			MemeCircus = AudioSystem.getClip();
			
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		}
		try {
			// opens clips
			MemeCircus.open(AudioSystem.getAudioInputStream(new File(
					"Audio//MemeCircusWav.wav")));

		} catch (LineUnavailableException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (UnsupportedAudioFileException e) {
			e.printStackTrace();
		}

	}

	public static void closeAll() {
		// stop all clips
		MemeCircus.stop();

	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}

}
