package com.example.autochekapplication

import androidx.fragment.app.Fragment
import dagger.hilt.android.AndroidEntryPoint

/**
 * To make android classes use Hilt, we have to annotate it with @AndroidEntryPoint
 */

@AndroidEntryPoint
class SavedFragment : Fragment(R.layout.fragment_saved) {

}