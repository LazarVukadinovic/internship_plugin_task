package com.example.internship_plugin_task

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.command.WriteCommandAction
import com.intellij.openapi.ui.Messages
import com.intellij.psi.PsiDirectory
import com.intellij.psi.PsiManager
import com.intellij.openapi.actionSystem.PlatformDataKeys


class CreatePackageAction : AnAction() {

    override fun actionPerformed(event: AnActionEvent) {
        val project = event.project ?: return
        val selectedFile = event.dataContext.getData(PlatformDataKeys.VIRTUAL_FILE)

        // Step 1: Check that we have a valid directory to work with
        val directory: PsiDirectory? = selectedFile?.let { PsiManager.getInstance(project).findDirectory(it) }
        if (directory == null) {
            Messages.showErrorDialog(project, "Please select a valid directory.", "Error")
            return
        }

        // Step 2: Prompt the user to enter a package name
        val packageName = Messages.showInputDialog(
            project,
            "Enter the name of the new package:",
            "Create New Package",
            Messages.getQuestionIcon()
        )?.trim()

        if (packageName.isNullOrEmpty()) {
            Messages.showErrorDialog(project, "No package name entered. Please try again.", "Error")
            return
        }

        // Step 3: Validate package name
        if (!isValidPackageName(packageName)) {
            Messages.showErrorDialog(project, "Invalid package name format.", "Error")
            return
        }

        // Step 4: Create the package structure
        WriteCommandAction.runWriteCommandAction(project) {
            try {
                createPackageStructure(directory, packageName)
                Messages.showInfoMessage(project, "Package '$packageName' created successfully!", "Success")
            } catch (e: Exception) {
                Messages.showErrorDialog(project, "Failed to create package: ${e.message}", "Error")
            }
        }
    }

    private fun isValidPackageName(name: String): Boolean {
        return name.matches(Regex("^[a-zA-Z_][a-zA-Z0-9]*$"))
    }

    private fun createPackageStructure(baseDir: PsiDirectory, packageName: String): PsiDirectory {
        var currentDir = baseDir
        val packageParts = packageName.split(".")

        for (part in packageParts) {
            // If subdirectory already exists, use it; otherwise, create it
            currentDir = currentDir.findSubdirectory(part) ?: currentDir.createSubdirectory(part)
        }

        return currentDir
    }
}