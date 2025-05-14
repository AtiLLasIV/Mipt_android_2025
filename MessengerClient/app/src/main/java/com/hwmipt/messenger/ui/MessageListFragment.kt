package com.hwmipt.messenger.ui

import android.os.Bundle
import android.view.*
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hwmipt.messenger.R
import com.hwmipt.messenger.viewmodel.ChatViewModel

class MessageListFragment : Fragment() {

    private lateinit var viewModel: ChatViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var editMessage: EditText
    private lateinit var btnSend: Button
    private lateinit var btnReturn: TextView
    private lateinit var adapter: MessageAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.fragment_message_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel = ViewModelProvider(requireActivity())[ChatViewModel::class.java]

        recyclerView = view.findViewById(R.id.list_messages)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        adapter = MessageAdapter()
        recyclerView.adapter = adapter

        btnReturn = view.findViewById(R.id.btn_return)
        editMessage = view.findViewById(R.id.edit_message)
        btnSend = view.findViewById(R.id.button_send)

        btnReturn.setOnClickListener {
            parentFragmentManager.popBackStack()
        }

        btnSend.setOnClickListener {
            val text = editMessage.text.toString().trim()
            if (text.isNotEmpty()) {
                viewModel.sendMessage(text)
                editMessage.text.clear()
            }
        }

        viewModel.messages.observe(viewLifecycleOwner) { messages ->
            adapter.submitList(messages)
        }

        viewModel.selectedChatId.observe(viewLifecycleOwner) { id ->
            if (id != null) {
                viewModel.loadMessages(id)
            }
        }
    }
}
