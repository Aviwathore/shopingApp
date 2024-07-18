package com.example.userinformation.intent.converttexttospeech

import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatButton
import androidx.fragment.app.Fragment
import com.example.userinformation.R
import com.google.android.material.textfield.TextInputEditText
import java.util.Locale

class TextToSpeechFragment : Fragment() {

    private lateinit var textMessage :TextInputEditText
    private lateinit var buttonText: AppCompatButton
    private lateinit var textToSpeech: TextToSpeech

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_text_to_speech, container, false)

        textMessage =view.findViewById(R.id.txt_to_speech)
        buttonText =view.findViewById(R.id.btn_txt_to_speech)

        textToSpeech = TextToSpeech(requireContext()){status ->
            if (status !=TextToSpeech.ERROR){

                textToSpeech.language= Locale.UK
            }
        }

        textMessage.hint = getString(R.string.enter_text_here)

        buttonText.setOnClickListener{
            if (::textToSpeech.isInitialized) {
                textToSpeech.speak(textMessage.text.toString(), TextToSpeech.QUEUE_FLUSH, null, "")
            }
        }
        return  view
    }


    fun closeFragment() {
        activity?.supportFragmentManager?.beginTransaction()?.remove(this)?.commit()
    }

    override fun onDestroyView() {
        textToSpeech.stop()
        textToSpeech.shutdown()
        super.onDestroyView()
    }

}