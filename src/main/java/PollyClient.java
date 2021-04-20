import com.amazonaws.ClientConfiguration;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.services.polly.AmazonPolly;
import com.amazonaws.services.polly.AmazonPollyClient;
import com.amazonaws.services.polly.AmazonPollyClientBuilder;
import com.amazonaws.services.polly.model.SynthesizeSpeechRequest;
import com.amazonaws.services.polly.model.SynthesizeSpeechResult;
import com.amazonaws.services.rekognition.AmazonRekognition;
import com.amazonaws.services.rekognition.AmazonRekognitionClientBuilder;

import java.io.InputStream;

public class PollyClient {
    public static void main(String[] args) {

        SynthesizeSpeechRequest request = new SynthesizeSpeechRequest();
        request.setText("First words.. Hello from Polly. This is my first effort at text to speech");
        request.setOutputFormat("mp3"); // ogg_vorbis or mp3 or pcm
        request.setVoiceId("Joey");
        // request.setVoiceId("Mizuki");


        AWSCredentialsProvider credentials = new ProfileCredentialsProvider();

        AmazonPolly pollyClient = AmazonPollyClientBuilder
                .standard()
                .withClientConfiguration(new ClientConfiguration())
                .withCredentials(credentials)
                .withRegion("us-east-1")
                .build();


        SynthesizeSpeechResult result = pollyClient.synthesizeSpeech(request);
        InputStream audio = result.getAudioStream();
        AudioStreamPlayer player = new AudioStreamPlayer();
        player.play(audio);
    }
}
