package com.ranggacikal.crudcomposewithdagger.component

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ranggacikal.crudcomposewithdagger.utils.AppColors

@Composable
fun CircularLoading() {
    CircularProgressIndicator(
        modifier = Modifier.size(50.dp).padding(16.dp),
        color = AppColors.mDarkPurple,
        strokeWidth = 4.dp
    )
}