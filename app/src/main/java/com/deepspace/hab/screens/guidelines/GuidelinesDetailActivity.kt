package com.deepspace.hab.screens.guidelines

import android.os.Bundle
import androidx.navigation.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.deepspace.common.base.BaseActivity
import com.deepspace.hab.R
import com.deepspace.hab.databinding.ActivityGuidelinesDetailBinding

class GuidelinesDetailActivity : BaseActivity<ActivityGuidelinesDetailBinding>() {

    override fun getViewBinding(): ActivityGuidelinesDetailBinding =
        ActivityGuidelinesDetailBinding.inflate(layoutInflater)

    private val args: GuidelinesDetailActivityArgs by navArgs()

    override fun create(savedInstanceState: Bundle?) {
        val guidelineAdapter = GuidelineListAdapter()
        binding.rvGuidelines.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.rvGuidelines.adapter = guidelineAdapter
        val communityList = listOf(
            "The HAB project should be done under supervision to prevent any accidents.",
            "The launching of HAB should not be conducted near public buildings or where people are there.",
            "Kids below 6 years old should not be allowed in the experiment because the latex can become a potential hazard and they can choke themselves.",
            "There should be no presence of inflammatory substances in the surroundings.",
            "Review the rules and regulations issued by the government for aviation so that the launch of HAB is done smoothly.",
            "Creation of an amiable and respectful environment is necessary for proper conduction of the project.",
            "The payload should not consist of sharp objects that can hurt the public during the recovery.",
            "The electrical components should be handled with care to prevent any short circuit."
        )

        val govtList = listOf(
            "The United States follow the FAA and FCC guidelines for launching the high altitude balloons. The  guidelines are as follows: ",
            "Cellphones are not allowed for tracking the payload",
            "Each package should be under 6lbs.",
            "The payload system should be under 12lbs.",
            "It should be less than 4 oz per cubic inch",
            "If a rope is attached then it should take less than 50 pounds of force to break it",
            "No person must operate a balloon that may create hazard to people",
            "We recommend  you to issue a NOTAM(notice to airmen) by contacting your local FAA AIR TRAFFIC CONTROL(ATC) before 6-24 hours of the launch and provide the details asked by them.",
            "For a more better understanding you can read the FAA and FCC guidelines and perform a legal and worry free launching of HAB.",
            "In India the DGCA guidelines are followed and the dgca standards are contained in the Civil Aviation Requirements(CAR).",
            "The Airports Authority Of India needs to be contacted regarding the flight operations atleast one month in advance for their approval. ",
            "Though the launching of HAB is a safe process but it can be prone to accident therefore you need to execute \"release of liability and indemnification agreement\".",
            "For other countries you can refer to your local FAA and FCC rules and regulations or contact your regional office for information about the launching of high altitude balloon."
        )

        when (args.type) {
            "community" -> {
                guidelineAdapter.submitList(communityList)
                binding.tvTitle.text = "Community Guidelines"
                binding.rvGuidelines.setBackgroundResource(R.drawable.ic_community_guidelines)
            }
            "govt" -> {
                guidelineAdapter.submitList(govtList)
                binding.tvTitle.text = "Government Protocols"
                binding.rvGuidelines.setBackgroundResource(R.drawable.ic_govt_protocols)
            }
        }
    }
}