package core.helpers;

import static org.monte.media.FormatKeys.EncodingKey;
import static org.monte.media.FormatKeys.FrameRateKey;
import static org.monte.media.FormatKeys.KeyFrameIntervalKey;
import static org.monte.media.FormatKeys.MIME_AVI;
import static org.monte.media.FormatKeys.MediaTypeKey;
import static org.monte.media.FormatKeys.MimeTypeKey;
import static org.monte.media.VideoFormatKeys.CompressorNameKey;
import static org.monte.media.VideoFormatKeys.DepthKey;
import static org.monte.media.VideoFormatKeys.ENCODING_AVI_TECHSMITH_SCREEN_CAPTURE;
import static org.monte.media.VideoFormatKeys.QualityKey;

import java.awt.AWTException;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.IOException;

import org.monte.media.Format;
import org.monte.media.FormatKeys.MediaType;
import org.monte.media.math.Rational;
import org.monte.screenrecorder.ScreenRecorder;

import core.ApplicationManager;
import core.base.HelperBase;

/**
 * Class for managing by screenshot and video recording
 * 
 */
public class ScreenLoggerHelper extends HelperBase
{
	public ScreenRecorder screenLogger = null;

	/**
	 * 
	 * @param p_manager
	 *            ApplicationManager instance
	 */
	public ScreenLoggerHelper(ApplicationManager p_manager)
	{
		super(p_manager);
	}

	/**
	 * Initializes graphic configurations
	 */
	public void init()
	{
		log().debug("Initialize graphic configuration for the screen");

		// Get the Graphics configuration of the Screen
		GraphicsConfiguration gc = GraphicsEnvironment
				.getLocalGraphicsEnvironment()
				.getDefaultScreenDevice()
				.getDefaultConfiguration();

		/* ScreenRecorder Configuration */
		try
		{
			screenLogger = new ScreenRecorder(gc, null,
					new Format(MediaTypeKey, MediaType.FILE, MimeTypeKey, MIME_AVI),
					new Format(MediaTypeKey, MediaType.VIDEO, EncodingKey, ENCODING_AVI_TECHSMITH_SCREEN_CAPTURE,
							CompressorNameKey, ENCODING_AVI_TECHSMITH_SCREEN_CAPTURE,
							DepthKey, 24, FrameRateKey, Rational.valueOf(15),
							QualityKey, 1.0f,
							KeyFrameIntervalKey, 15 * 60),
					new Format(MediaTypeKey, MediaType.VIDEO, EncodingKey, "black",
							FrameRateKey, Rational.valueOf(30)),
					null, new File(manager.project().config().SCREENLOGGER_FOLDER));

			log().debug("Graphic configuration for the screen is initializated successfully");

		}
		catch (IOException p_ex)
		{
			log().warn("Cannot initialize graphic configuration for the screen: " + p_ex);
		}
		catch (AWTException p_ex)
		{
			log().warn("Cannot initialize graphic configuration for the screen: " + p_ex);
		}

	}

	/**
	 * Starts the ScreenRecorder
	 */
	public void start()
	{
		log().debug("Start the ScreenLogger");

		try
		{
			screenLogger.start();
			log().debug("ScreenLogger is started successfully");
		}
		catch (IOException p_ex)
		{
			log().warn("Cannot start the ScreenLogger: " + p_ex);
		}
	}

	/**
	 * Stops the ScreenRecorder
	 */
	public void stop()
	{
		log().debug("Stop the ScreenLogger");

		try
		{
			if (config().SCREENLOGGER)
			{
				screenLogger.stop();
				log().debug("ScreenLogger is stopped successfully");
			}
			else
			{
				log().debug("Cannot stop the ScreenLogger because it was not running");
			}
		}
		catch (Exception p_ex)
		{
			log().warn("Cannot stop the ScreenLogger: " + p_ex);
		}
	}
}
