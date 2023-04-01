package com.cajusoftware.application.fakedocumentgenerator

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.cajusoftware.application.fakedocumentgenerator.databinding.FragmentFirstBinding
import com.cajusoftware.fakedocumentgenerator.generators.FederationUnit
import com.cajusoftware.fakedocumentgenerator.generators.cpf.CpfGenerator

class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val cpfGenerator: CpfGenerator by lazy {
        CpfGenerator.Builder()
            .withSymbols(true)
            .setFederationUnit(FederationUnit.RN)
            .build()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonFirst.setOnClickListener {

            viewLifecycleOwner.lifecycleScope.launchWhenCreated {
                cpfGenerator.generateCpfSet(5)
            }

            binding.textviewFirst.text = cpfGenerator.generateCpf()
//            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}