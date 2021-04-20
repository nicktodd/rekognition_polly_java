
import com.amazonaws.ClientConfiguration;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.services.rekognition.AmazonRekognition;
import com.amazonaws.services.rekognition.AmazonRekognitionClient;
import com.amazonaws.services.rekognition.AmazonRekognitionClientBuilder;
import com.amazonaws.services.rekognition.model.*;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

public class IndexAndListFacesExample {

    public static final String S3_BUCKET = "facesusconygrecom";

    public static void main(String[] args) {
        AWSCredentialsProvider credentials = new ProfileCredentialsProvider();

        AmazonRekognition amazonRekognition = AmazonRekognitionClientBuilder
                .standard()
                .withClientConfiguration(new ClientConfiguration())
                .withCredentials(credentials)
                .withRegion("us-east-1")
                .build();

        CompareFacesRequest compareFacesRequest = new CompareFacesRequest();

        compareFacesRequest.setSourceImage(getImageUtil(S3_BUCKET, "sarah.jpg"));
        compareFacesRequest.setTargetImage(getImageUtil(S3_BUCKET, "family.jpg"));

        CompareFacesResult compareFacesResult = amazonRekognition.compareFaces(compareFacesRequest);
        
        List<CompareFacesMatch> matchingFaces = compareFacesResult.getFaceMatches();
        matchingFaces.forEach(m -> System.out.println(m.getFace().getConfidence()));

    }

    private static Image getImageUtil(String bucket, String key) {
        return new Image().withS3Object(new S3Object().withBucket(bucket).withName(key));
    }
}