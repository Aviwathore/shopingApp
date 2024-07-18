package com.example.userinformation.cardoptionforpayment

import android.Manifest
import android.animation.AnimatorInflater
import android.animation.AnimatorSet
import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.content.pm.PackageManager
import android.content.res.Resources
import android.media.RingtoneManager
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.fragment.app.Fragment
import com.example.userinformation.R
import com.example.userinformation.dashboard.DashBoardActivity
import com.example.userinformation.databinding.FragmentCreditCardBinding
import com.example.userinformation.dbHelper.ProductDBHelper
import com.example.userinformation.paymentsuccess.PaymentSuccessFragment
import com.example.userinformation.sendPushNotification
import com.google.firebase.messaging.FirebaseMessaging
import com.ncorti.slidetoact.SlideToActView
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale


class CreditCardFragment : Fragment(), OnClickListener{
    private lateinit var binding: FragmentCreditCardBinding
    private var calendar = Calendar.getInstance()
    private var cleanText = ""
    private var isMonthYearPickerShown = false
    private lateinit var dbHelper: ProductDBHelper
    private var isVisibleDatePicker: DatePickerDialog? = null
    private var datePickerDialog = context?.let { DatePickerDialog(it) }

    private lateinit var front_anim: AnimatorSet
    private lateinit var back_anim: AnimatorSet
    var isFront = true


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentCreditCardBinding.inflate(layoutInflater, container, false)

        binding.headerLayout.txtHeader.text = getString(R.string.credit_card)
        binding.headerLayout.buttonStart.setOnClickListener(this)
        binding.editValidUpto.setOnClickListener(this)
        binding.editCvv.setOnClickListener(this)
        holderNameTextWatcher()
        holderCardNumberTextWatcher()
        cardCvvTextWatcher()

        binding.swipePayButton.isEnabled = false


        dbHelper = ProductDBHelper(requireContext())

        isValidInputField()
        return binding.root
    }

    private fun isValidInputField() {

        if (binding.editCardNumbers.text.length == 19 &&
            binding.editValidUpto.text.isNotEmpty() && binding.editHolderNames.text.isNotEmpty() && binding.editCvv.text.length == 3
        ) {

            binding.swipePayButton.isEnabled = true

            binding.swipePayButton.outerColor = resources.getColor(R.color.crimson)
            binding.swipePayButton.onSlideCompleteListener =
                object : SlideToActView.OnSlideCompleteListener {
                    override fun onSlideComplete(view: SlideToActView) {
                        eventCallPaymentSuccessFragment()

                    }

                }
        } else {

            binding.swipePayButton.isEnabled = false
            binding.swipePayButton.outerColor = resources.getColor(R.color.dark_gray)

        }

    }

    private fun eventCallPaymentSuccessFragment(): SlideToActView.OnSlideCompleteListener? {

        dbHelper.updateCardHolderDetails(
            binding.editHolderNames.text.toString(), binding.txtCardNumbers.text.toString(),
            binding.editValidUpto.text.toString()
        )

        val dashBoardActivity = activity as DashBoardActivity
        dashBoardActivity.replaceFragment(PaymentSuccessFragment())

        dbHelper.updateOrderConfirmStatus(1, 0, "yes", 1, 0, 0)


        FirebaseMessaging.getInstance().token.addOnCompleteListener{
            if (it.isSuccessful){
                Log.d("TAG", "Fire base token: -------------"+it.result.toString())
                sendPushNotification(it.result.toString(), "Confirm Order", "You are order confirmation is done.")
            }
        }
        clearTextFields()


        return null
    }

    private fun sendNotification() {
        Log.d("TAG", "sendNotification: ---------------------- ")
        val title = "Order Confirm"
        val description = "This order is confirm."

        val builder = NotificationCompat.Builder(requireContext())
            .setSmallIcon(R.drawable.cube)
            .setContentTitle(title)
            .setContentText(description)
            .setAutoCancel(true)
            .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))

        if (ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.POST_NOTIFICATIONS
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            return
        }
        NotificationManagerCompat.from(requireContext()).notify(0, builder.build())
    }

    private fun cardCvvTextWatcher() {
        binding.editCvv.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                binding.txtCvvCard.text = s.toString()
                if (s?.length == 3) {
                    binding.editCvv.setBackgroundResource(R.drawable.black_mobile_border)
                } else {
                    binding.editCvv.setBackgroundResource(R.drawable.border_color)

                }
            }

            override fun afterTextChanged(s: Editable?) {
                isValidInputField()
            }

        })
    }


    private fun holderCardNumberTextWatcher() {
        binding.editCardNumbers.addTextChangedListener(object : TextWatcher {
            private var previousFirstChar: Char? = null

            @SuppressLint("ResourceType")
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                front_anim = AnimatorInflater.loadAnimator(
                    requireContext(),
                    R.anim.card_front_animator
                ) as AnimatorSet
                back_anim = AnimatorInflater.loadAnimator(
                    requireContext(),
                    R.anim.card_back_animator
                ) as AnimatorSet
                if (!s.isNullOrEmpty()) {

                    val firstChar = s[0]

                    if (firstChar != previousFirstChar) {

                        previousFirstChar = firstChar

                        if (firstChar == '5') {

                            binding.imgVisa.setImageResource(R.drawable.master_card)

                            binding.rlCreditCard.setBackgroundResource(R.drawable.master_card_background_drawable)

                        } else if (firstChar == '4') {

                            binding.imgVisa.setImageResource(R.drawable.visa)

                            binding.rlCreditCard.setBackgroundResource(R.drawable.visa_card_background_drawable)

                        } else if (firstChar == '3') {

                            binding.imgVisa.setImageResource(R.drawable.american_express)

                            binding.rlCreditCard.setBackgroundResource(R.drawable.american_express_card_background_drawable)

                            binding.imgEmvCard.setImageResource(R.drawable.emv_american_express_chip)

                        } else {

                            binding.imgVisa.setImageResource(R.drawable.discover_card)

                            binding.imgEmvCard.setImageResource(R.drawable.emv_american_express_chip)

                            binding.rlCreditCard.setBackgroundResource(R.drawable.discover_card_background_drawable)

                        }

                        front_anim.setTarget(binding.rlCreditCard)

                        back_anim.setTarget(binding.rlCreditCard)

                        front_anim.start()

                        back_anim.start()

                        isFront = false

                    }

                }

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                binding.txtCardNumbers.text = s.toString()
                if (binding.txtCardNumbers.text.length < 19) {
                    binding.editCardNumbers.setBackgroundResource(R.drawable.border_color)
                } else {
                    binding.editCardNumbers.setBackgroundResource(R.drawable.black_mobile_border)
                }

                if (before == 0 && count == 0) {
                    if (start >= 0) {
                        binding.editCardNumbers.setSelection(start)
                    }
                    return
                }

                cleanText = s.toString().replace("-", "")
                val formattedText = StringBuilder()

                var index = 0
                for (element in cleanText) {
                    formattedText.append(element)
                    index++
                    if (index % 4 == 0 && index < 19) {
                        formattedText.append("-")
                    }
                }
                val newCursorPosition = when {
                    count > before -> {
                        19
                    }

                    else -> {
                        start - (before / 4)

                    }
                }

                binding.editCardNumbers.removeTextChangedListener(this)
                binding.editCardNumbers.setText(formattedText)

                binding.editCardNumbers.setSelection(
                    maxOf(0, minOf(newCursorPosition, formattedText.length))
                )

                binding.editCardNumbers.addTextChangedListener(this)
                isValidInputField()
            }

            @SuppressLint("ResourceType")
            override fun afterTextChanged(s: Editable?) {

            }


        })
    }


    private fun holderNameTextWatcher() {

        binding.editHolderNames.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                binding.txtHolderNames.text = s.toString()
            }

            override fun afterTextChanged(s: Editable?) {
                if (binding.editHolderNames.text.isEmpty()) {
                    binding.editHolderNames.setBackgroundResource(R.drawable.border_color)
                } else {
                    binding.editHolderNames.setBackgroundResource(R.drawable.black_mobile_border)
                }

                isValidInputField()
            }

        })
    }

    private fun mothYearDatePickerShown() {
        if (!isMonthYearPickerShown) {
            showCardExpiryDate()
            isMonthYearPickerShown = true
        }
    }

    override fun onClick(v: View?) {

        when (v?.id) {
            R.id.button_start -> activity?.onBackPressed()
            R.id.edit_valid_upto ->
                mothYearDatePickerShown()
        }

    }

    private fun showCardExpiryDate() {
        if (context == null) {
            return
        }

        datePickerDialog = DatePickerDialog(
            requireContext(),
            R.style.CustomDatePickerStyle,
            { _, year, month, _ ->
                val selectedDate = Calendar.getInstance()
                selectedDate.set(year, month, 1)
                val dateFormat = SimpleDateFormat("MM/yy", Locale.getDefault())
                val formatDate = dateFormat.format(selectedDate.time)
                binding.editValidUpto.text = formatDate
                binding.txtExpiryDates.text = formatDate
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )

        datePickerDialog!!.setOnShowListener {
            val datePicker = datePickerDialog!!.datePicker
            val dayPicker = datePicker.findViewById<View>(
                Resources.getSystem().getIdentifier("day", "id", "android")
            )
            dayPicker.visibility = View.GONE
        }
        datePickerDialog!!.datePicker.minDate = calendar.timeInMillis
        datePickerDialog!!.window?.setLayout(600, ViewGroup.LayoutParams.WRAP_CONTENT)
        datePickerDialog!!.setCancelable(false)
        datePickerDialog!!.show()

        datePickerDialog!!.setOnDismissListener {
            isMonthYearPickerShown = false
            isValidInputField()
        }
    }


    private fun clearTextFields() {

        binding.editCardNumbers.setText("")
        binding.editCvv.setText("")
        binding.editHolderNames.setText("")
        binding.editValidUpto.text = ""
        binding.txtCardNumbers.text = ""
        binding.txtCvvCard.text = ""
        binding.txtHolderNames.text = ""
        binding.txtExpiryDates.text = ""


    }

    override fun onDestroy() {
        super.onDestroy()
        if (isVisibleDatePicker != null && isVisibleDatePicker?.isShowing == false) {
            datePickerDialog?.dismiss()
        }
    }
}




