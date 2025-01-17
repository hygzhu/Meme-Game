import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;


//AUDIO IS GLITCHY AND MESSED UP
//WHATCHASAY SOUND DOESNT STOP PLAYING, NEED TIMER
//SAD MUSIC SOMETIMES PLAYS AFTER USER GOES BACK TO MENU

public class Audio implements Runnable {

	AudioInputStream audio;
	static Clip MemeCircus, Sad, Whatchasay;

	public static void run(String path) {
		// play sound file

		if (path.equals("MemeCircus")) {
			if(MemeCircus.isRunning() != true){	
				closeAll();
				MemeCircus.start();
				MemeCircus.loop(100);
			}		
		}else if (path.equals("Sad")) {
			if(Sad.isRunning() != true){
				closeAll();
				Sad.start();
				Sad.loop(100);
			}
		}else if(path.equals("Whatchasay")){
			increase(Whatchasay);
			Whatchasay.start();
		
		}
	

	}


	public static void initialize() {
		// Finding sound file

		try {
			// initializes all audio clips
			MemeCircus = AudioSystem.getClip();
			Sad = AudioSystem.getClip();
			Whatchasay = AudioSystem.getClip();
			
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		}
		try {
			// opens clips
			MemeCircus.open(AudioSystem.getAudioInputStream(new File(
					"Audio//MemeCircusWav.wav")));
			Sad.open(AudioSystem.getAudioInputStream(new File(
					"Audio//sad.wav")));
			Whatchasay.open(AudioSystem.getAudioInputStream(new File(
					"Audio//whatchasay.wav")));

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
		Sad.stop();
		Whatchasay.stop();

	}
	
	public static int increase(Clip clip) {

		// increase volume
		FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);

		if (gainControl.getValue() + 5 <= 6) {
			gainControl.setValue(gainControl.getValue() + 5.0f);
			return 1;
		}

		return 0;

	}

	public static int decrease(Clip clip) {
		// decrease volume
		FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);

		if (gainControl.getValue() - 5 >= -20) {
			gainControl.setValue(gainControl.getValue() - 5.0f);
			return -1;
		}
		return 0;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}

}
