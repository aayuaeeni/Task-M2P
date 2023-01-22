package com.raju.m2p.presentation.player

import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.MediaController
import com.raju.m2p.bean.playlist.ResultBean
import com.raju.m2p.databinding.ActivityPlayerBinding
import com.raju.m2p.presentation.base.BaseActivity

class PlayerActivity : BaseActivity() {
    private lateinit var binding: ActivityPlayerBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPlayerBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupPlayerView()
        initActions()
    }

    private fun initActions() {
        binding.ivBack.setOnClickListener {
            finish()
        }
    }

    private fun setupPlayerView() {
        val data = intent.getSerializableExtra("data") as ResultBean
        binding.tvTrackTitle.text = data.trackName
        binding.tvTrackName.text = data.artistName
        val uri: Uri = Uri.parse(data.previewUrl)
        binding.videoView.setVideoURI(uri)
        val mediaController = MediaController(this)
        mediaController.setAnchorView(binding.videoView)
        mediaController.setMediaPlayer(binding.videoView)
        binding.videoView.setMediaController(mediaController)
        binding.videoView.start()

        binding.videoView.setOnPreparedListener {
            binding.progressBar.visibility = View.GONE
        }
    }
}