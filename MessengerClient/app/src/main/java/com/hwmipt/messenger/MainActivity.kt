package com.hwmipt.messenger

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.hwmipt.messenger.data.ChatRepository
import com.hwmipt.messenger.data.RetrofitInstance
import com.hwmipt.messenger.ui.ChatListFragment
import com.hwmipt.messenger.ui.MessageListFragment
import com.hwmipt.messenger.viewmodel.ChatViewModel
import com.hwmipt.messenger.viewmodel.ChatViewModelFactory

class MainActivity : AppCompatActivity() {

    lateinit var viewModel: ChatViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val repository = ChatRepository(RetrofitInstance.api)
        val factory = ChatViewModelFactory(repository)
        viewModel = ViewModelProvider(this, factory)[ChatViewModel::class.java]

        if (findViewById<View?>(R.id.message_fragment) != null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.chat_fragment, ChatListFragment())
                .replace(R.id.message_fragment, MessageListFragment())
                .commit()

        } else {
            val fragment = if (viewModel.selectedChatId.value != null) {
                MessageListFragment()
            } else {
                ChatListFragment()
            }

            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .commit()
        }
    }
}