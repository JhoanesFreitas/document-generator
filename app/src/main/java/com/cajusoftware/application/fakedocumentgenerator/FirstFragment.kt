package com.cajusoftware.application.fakedocumentgenerator

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.cajusoftware.application.fakedocumentgenerator.databinding.FragmentFirstBinding
import com.cajusoftware.fakedocumentgenerator.generators.FederationUnit
import com.cajusoftware.fakedocumentgenerator.generators.cnpj.CnpjGenerator
import com.cajusoftware.fakedocumentgenerator.generators.cpf.CpfGenerator
import com.cajusoftware.fakedocumentgenerator.generators.rg.RgGenerator

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

    private val rgGenerator: RgGenerator by lazy {
        RgGenerator.Builder().withSymbols(true).build()
    }

    private val cnpjGenerator: CnpjGenerator by lazy {
        CnpjGenerator.Builder()
            .withSymbols(true)
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

//            binding.textviewFirst.text = cpfGenerator.generateCpf()
            binding.textviewFirst.text = cnpjGenerator.generateCnpj()
//            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}