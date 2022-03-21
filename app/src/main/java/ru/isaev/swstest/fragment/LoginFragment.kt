package ru.isaev.swstest.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ru.isaev.swstest.fragment.viewmodel.LoginViewModel
import ru.isaev.swstest.helper.utils.AppTextWatcher
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import ru.isaev.swstest.databinding.LoginFragmentBinding

@AndroidEntryPoint
class LoginFragment : Fragment() {

    private val viewModel by viewModels<LoginViewModel>()
    lateinit var mBinding: LoginFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mBinding = LoginFragmentBinding.inflate(layoutInflater)
        return mBinding.root

    }

    override fun onStart() {
        super.onStart()
        userLogin(viewModel)
        mBinding.registerButton.setOnClickListener { viewModel.sendLoginRequest(viewModel.user) }

    }

    private fun userLogin(viewModel: LoginViewModel) {
        mBinding.emailEditText.addTextChangedListener(AppTextWatcher {
            if (mBinding.passwordEditText.text.toString().isNotEmpty()) {
                viewModel.user.email = mBinding.emailEditText.text.toString()
            }
        })
        mBinding.passwordEditText.addTextChangedListener(AppTextWatcher {
            if (mBinding.passwordEditText.text.toString().isNotEmpty()) {
                viewModel.user.password = mBinding.passwordEditText.text.toString().toInt()
            }

        }
        )

    }


}
