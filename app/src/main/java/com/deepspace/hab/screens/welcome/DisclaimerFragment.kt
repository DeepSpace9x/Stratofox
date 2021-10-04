package com.deepspace.hab.screens.welcome

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import com.deepspace.common.base.BaseFragment
import com.deepspace.hab.databinding.FragmentDisclaimerBinding
import com.deepspace.hab.screens.HomeActivity
import com.deepspace.hab.screens.HomeViewModel
import com.deepspace.hab.screens.SplashScreenActivity
import timber.log.Timber

class DisclaimerFragment : BaseFragment<FragmentDisclaimerBinding>() {

    override fun getViewBinding(): FragmentDisclaimerBinding =
        FragmentDisclaimerBinding.inflate(layoutInflater)

    private val homeViewModel: HomeViewModel by activityViewModels()
    private val shouldEnable = MutableLiveData(false)
    private var isFirstChecked = false
    private var isSecondChecked = false
    private var isThirdChecked = false

    override fun onViewCreated(savedInstanceState: Bundle?) {
        val disclaimerAdapter = DisclaimerListAdapter()
        binding.rvDisclaimer.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.rvDisclaimer.adapter = disclaimerAdapter
        val list = listOf(
            "The experiments in this application is based upon the research conducted by team at StratoFox and meant to be considered as a personal guide or standalone reference for learning about high altitude balloons.",
            "All the experiments in this app, including procuring the equipments, assembling the modules, designing the experiment, testing and launch and retrieval of the payload has been designed in such a way that they don't harm the individuals (students and mentors) before or during this experiment. Still, as a precautionary step, we advise the students to conduct these experiments under a proper supervision and guidance. They are free to chose their mentors during this activity and can be anyone who is having basic knowledge of assembling and operations (like teachers, community mentors and parents).",
            "It is advisable to conduct these experiments in an open space ground, with proper directions and guidance. It is suggested to perform the activities within a closed range and there should not be any public institutions, industrial factories, offices, electrical or nuclear plants or water bodies (like rivers, ponds, etc) within a certain diameter as mentioned in the pre-requisite of the app.",
            "The team at StratoFox (or their entities) will not be liable for any indirect, incidental, special, consequential or punitive damages, or any loss of data or equipment or other intangible losses. The individual(s) shall take the responsibility of whatever the outcomes or process is involved in this experiment.",
            "Unless otherwise indicated, this application is the proprietary property of team at StratoFox, and all the source code, databases, functionality, software, user interface designs, audio, video, texts and photographs, and all the research content shall be protected and copyrighted. The team shall have the power to control the distribution, open source and reuse the contents. No duplication or unfair use is allowed."
        )
        disclaimerAdapter.submitList(list)


        binding.checkboxCond1.setOnCheckedChangeListener { _, isChecked ->
            isFirstChecked = isChecked
            updateShouldEnable()
        }
        binding.checkboxCond2.setOnCheckedChangeListener { _, isChecked ->
            isSecondChecked = isChecked
            updateShouldEnable()
        }
        binding.checkboxCond3.setOnCheckedChangeListener { _, isChecked ->
            isThirdChecked = isChecked
            updateShouldEnable()
        }

        shouldEnable.observe(viewLifecycleOwner, {
            binding.tvAgreeBtn.isEnabled = it
        })
        binding.tvAgreeBtn.setOnClickListener {
            val intent = Intent(requireActivity(), HomeActivity::class.java)
            Timber.d("Got Module List Disclaimer Fragment: ${homeViewModel.moduleList}")
            intent.putParcelableArrayListExtra(SplashScreenActivity.MODULE_LIST, ArrayList(homeViewModel.moduleList!!))
            startActivity(intent)
            requireActivity().finish()
        }
    }

    private fun updateShouldEnable() {
        if (isFirstChecked && isSecondChecked && isThirdChecked) {
            shouldEnable.postValue(true)
        } else
            shouldEnable.postValue(false)
    }

}