package com.mygdx.game.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonWriter;
import com.mygdx.game.util.audio.PausablePlayer;
import com.mygdx.game.util.audio.analysis.SpectrumProvider;
import com.mygdx.game.util.audio.analysis.ThresholdFunction;
import com.mygdx.game.util.audio.io.MP3Decoder;
import javafx.util.Pair;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


/**
 * Generuje kulki na podstawie muzyki
 */
public class Test {

//    public static final String FILE = "holiday.mp3";
    private static final int HOP_SIZE = 512;
    private static final int HISTORY_SIZE = 50;
    private static final float[] multipliers = { 1.5f, 1.5f, 1.5f };
    private static final float[] bands = { 80, 1000, 4000, 10000, 10000, 16000 };
    private String path;
    private PausablePlayer player;

    public Test(String path) {
        this.path = path;
    }

    public void test() throws Exception {

        try {
            FileInputStream input = new FileInputStream("pianinko.mp3");
            player = new PausablePlayer(input);
            player.play();
        } catch (final Exception e) {
            throw new RuntimeException(e);
        }

        MP3Decoder decoder = new MP3Decoder( new FileInputStream( path  ) );
        SpectrumProvider spectrumProvider = new SpectrumProvider( decoder, 1024, HOP_SIZE, true );
        float[] spectrum = spectrumProvider.nextSpectrum();
        float[] lastSpectrum = new float[spectrum.length];
        List<List<Float>> spectralFlux = new ArrayList<List<Float>>( );
        List<List<Float>> prunedSpectralFlux = new ArrayList<List<Float>>( );
        List<Pair<Integer, Float>> peaks = new ArrayList<Pair<Integer, Float>>( );
        for( int i = 0; i < bands.length / 2; i++ ) {
            spectralFlux.add( new ArrayList<Float>( ) );
            prunedSpectralFlux.add( new ArrayList<Float>( ) );
        }

        do {
            for( int i = 0; i < bands.length; i+=2 ) {
                int startFreq = spectrumProvider.getFFT().freqToIndex( bands[i] );
                int endFreq = spectrumProvider.getFFT().freqToIndex( bands[i+1] );
                float flux = 0;
                for( int j = startFreq; j <= endFreq; j++ ) {
                    float value = (spectrum[j] - lastSpectrum[j]);
                    value = (value + Math.abs(value))/2;
                    flux += value;
                }
                spectralFlux.get(i/2).add( flux );
            }

            System.arraycopy( spectrum, 0, lastSpectrum, 0, spectrum.length );
        } while( (spectrum = spectrumProvider.nextSpectrum() ) != null );

        List<List<Float>> thresholds = new ArrayList<List<Float>>( );
        for( int i = 0; i < bands.length / 2; i++ ) {
            List<Float> threshold = new ThresholdFunction( HISTORY_SIZE, multipliers[i] ).calculate( spectralFlux.get(i) );
            thresholds.add( threshold );
        }

        for( int j = 0; j < thresholds.size(); j++ ) {
            for( int i = 0; i < thresholds.get(j).size(); i++ ) {
                if( thresholds.get(j).get(i) <= spectralFlux.get(j).get(i) ) {
					prunedSpectralFlux.get(j).add( spectralFlux.get(j).get(i) - thresholds.get(j).get(i) );
                } else {
					prunedSpectralFlux.get(j).add( (float)0 );
                }
            }
        }

        for( int i = 0; i < prunedSpectralFlux.get(0).size()-1; i++ ) {
            for( int j = 0; j < prunedSpectralFlux.size(); j++ ) {
                if( prunedSpectralFlux.get(j).get(i) > prunedSpectralFlux.get(j).get(i+1) ) {
                    peaks.add(new Pair<>(j, prunedSpectralFlux.get(j).get(i)));
                } else {
                    peaks.add(new Pair<>(j, new Float(0)));
                }
            }
        }

        Level times = new Level();

        float last = 0;
        float current;
        for(int i = 1; i < peaks.size() - 1; i++) {
            if(peaks.get(i).getValue() > peaks.get(i-1).getValue() && peaks.get(i).getValue() > peaks.get(i+1).getValue() ) {

                if(i < peaks.size() / 3) {
                    current =  i * (1024f / 44100);
                } else if(i < peaks.size() / 3 * 2){
                    current = (i - peaks.size() / 3) * (1024f / 44100);
                } else {
                    current = (i - peaks.size() / 3 * 2) * (1024f / 44100);
                }
                Random random = new Random((0xDEADBEEF));
                if(current > last + 0.15 || random.nextFloat() > 0.95f) {
                    last = current;
                    times.lanes.get( peaks.get(i).getKey() ).add( last/2 );
                }
            }
        }

        // Save times to json
        Json json = new Json();
        json.setOutputType(JsonWriter.OutputType.json);
        FileHandle level = Gdx.files.local("levels/test2.json");
        level.writeString(json.prettyPrint(times), false);
        player.stop();
    }

}
