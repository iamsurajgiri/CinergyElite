package tuna.cinergyelite.info

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import tuna.cinergyelite.R
import tuna.cinergyelite.databinding.FragmentInfoBinding
import tuna.cinergyelite.utils.navigateTo
import tuna.core.data.model.response.EscapeRoomMovie


class InfoFragment : BottomSheetDialogFragment() {

    private var _binding: FragmentInfoBinding? = null
    private val binding get() = _binding!!

    private val escapeRoomMovie: EscapeRoomMovie by lazy {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            arguments?.getSerializable("escapeRoomMovie",EscapeRoomMovie::class.java) as EscapeRoomMovie
        } else {
            arguments?.getSerializable("escapeRoomMovie") as EscapeRoomMovie
        }
    }

    private val tickets by lazy {
        arguments?.getString("er_tickets")?:"0"
    }
    override fun onDestroyView() {
        super.onDestroyView()
        //set binding to null when fragment is destroyed
        _binding = null
    }



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding= FragmentInfoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            title.text = escapeRoomMovie.title
            description.text = escapeRoomMovie.synopsis
            binding.duration.text = escapeRoomMovie.runTime
            binding.members.text = tickets
            Glide.with(requireContext()).load(escapeRoomMovie.imageUrl).into(binding.poster)
            close.setOnClickListener {
                dismiss()
            }
            bookNow.setOnClickListener {
                findNavController().navigateTo(
                    R.id.bookingFragment,
                    bundleOf(
                        "movieId" to escapeRoomMovie.id
                    )
                )
                dismiss()
            }
        }
    }

    companion object {

        @JvmStatic
        fun newInstance(escapeRoomMovie: EscapeRoomMovie, tickets: String) =
            InfoFragment().apply {
                arguments = Bundle().apply {
                    putSerializable("escapeRoomMovie", escapeRoomMovie)
                    putString("er_tickets",tickets)
                }
            }
    }
}