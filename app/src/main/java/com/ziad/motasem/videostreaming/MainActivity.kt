package com.ziad.motasem.videostreaming

import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.media2.exoplayer.external.ExoPlayerFactory
import androidx.media2.exoplayer.external.ExoPlayerFactory.*
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.upstream.DataSource
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import com.ziad.motasem.videostreaming.databinding.ActivityMainBinding



class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var player:SimpleExoPlayer
    val video1URL = "https://learningcontainer.com/wp-content/uploads/2020/05/sample-mp4-file.mp4"
    val video2URL = "https://learningcontainer.com/wp-content/uploads/2020/05/sample-mp4-file.mp4"
    val video3URL = "https://learningcontainer.com/wp-content/uploads/2020/05/sample-mp4-file.mp4"
    val video4URL = "https://learningcontainer.com/wp-content/uploads/2020/05/sample-mp4-file.mp4"

    private var playWhenReady = true
    private var currentWindow = 0
    private var playBackPosition = 0L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnVideo1.setOnClickListener {
            initVideo(video1URL)
            releaseVideo()
        }
         binding.btnVideo2.setOnClickListener {
            initVideo(video2URL)
            releaseVideo()
        }
         binding.btnVideo3.setOnClickListener {
            initVideo(video3URL)
            releaseVideo()
        }
         binding.btnVideo4.setOnClickListener {
            initVideo(video4URL)
            releaseVideo()
        }

    }

    private fun initVideo(videoURL:String){
        player = ExoPlayerFactory.newSimpleInstance(this)

        binding.videoPlayer.player = player

        val uri = Uri.parse(videoURL)

        val dataSourceFactory = DefaultDataSourceFactory(this, "explorer-codelab")

        val mediaSource = ProgressiveMediaSource.Factory(dataSourceFactory).createMediaSource(uri)

        player.playWhenReady = playWhenReady
        player.seekTo(currentWindow, playBackPosition)
        player.prepare(mediaSource, false, false)
    }

    private fun releaseVideo(){
        if(player != null){
            playWhenReady = player.playWhenReady
            playBackPosition = player.currentPosition
            currentWindow = player.currentWindowIndex
            player.release()
            player = null!!
        }
    }

    override fun onStart() {
        super.onStart()
        initVideo(video1URL)
    }

    override fun onPause() {
        super.onPause()
        if(player != null){
            releaseVideo()
        }
    }

    override fun onStop() {
        super.onStop()
        releaseVideo()
    }

    override fun onResume() {
        super.onResume()
        releaseVideo()
    }
}