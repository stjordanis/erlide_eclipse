/*******************************************************************************
 * Copyright (c) 2004 Eric Merritt and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Eric Merritt
 *******************************************************************************/
package org.erlide.ui.perspectives;

import org.eclipse.debug.ui.IDebugUIConstants;
import org.eclipse.search.ui.NewSearchUI;
import org.eclipse.ui.IFolderLayout;
import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;
import org.eclipse.ui.console.IConsoleConstants;
import org.eclipse.ui.progress.IProgressConstants;
import org.erlide.ui.IErlideUIConstants;
import org.erlide.ui.views.console.ErlangConsoleView;
import org.erlide.ui.views.eval.LiveExpressionsView;
import org.erlide.ui.views.processlist.ProcessListView;

/**
 * The erlang perspective.
 * 
 * 
 * @author Eric Merritt [cyberlync at yahoo dot com]
 */
public class ErlangPerspective implements IPerspectiveFactory {

	/**
	 * Erlang perspecive id
	 */
	public static final String ID = "org.erlide.ui.perspectives.ErlangPerspective";

	/**
	 * @see org.eclipse.ui.IPerspectiveFactory#createInitialLayout(org.eclipse.ui.IPageLayout)
	 */
	public void createInitialLayout(IPageLayout layout) {
		defineActions(layout);
		defineLayout(layout);
		defineShortcuts(layout);
	}

	/**
	 * The action objects Define the actions
	 * 
	 * @param layout
	 */
	private void defineActions(IPageLayout layout) {
		layout.addActionSet(IDebugUIConstants.LAUNCH_ACTION_SET);
		layout.addActionSet(IDebugUIConstants.DEBUG_ACTION_SET);
		layout.addActionSet(IPageLayout.ID_NAVIGATE_ACTION_SET);
	}

	/**
	 * Define the layout of the editor and views
	 * 
	 * @param layout
	 *            the layout object
	 */
	private void defineLayout(IPageLayout layout) {
		final String editorArea = layout.getEditorArea();

		final IFolderLayout left = layout.createFolder("left",
				IPageLayout.LEFT, (float) 0.2, editorArea);
		// left.addView(IPageLayout.ID_RES_NAV);
		left.addView(IErlideUIConstants.NAVIGATOR_VIEW_ID);

		final IFolderLayout bottom = layout.createFolder("bottom",
				IPageLayout.BOTTOM, (float) 0.65, editorArea);
		bottom.addView(ErlangConsoleView.ID);
		bottom.addView(ProcessListView.ID);
		bottom.addView(IPageLayout.ID_PROBLEM_VIEW);
		bottom.addView(LiveExpressionsView.ID);
		bottom.addPlaceholder(NewSearchUI.SEARCH_VIEW_ID);
		bottom.addPlaceholder(IPageLayout.ID_BOOKMARKS);
		bottom.addPlaceholder(IProgressConstants.PROGRESS_VIEW_ID);

		layout.addView(IPageLayout.ID_OUTLINE, IPageLayout.RIGHT, (float) 0.7,
				editorArea);

	}

	private void defineShortcuts(IPageLayout layout) {
		// views - search
		layout.addShowViewShortcut(NewSearchUI.SEARCH_VIEW_ID);

		//
		layout.addShowViewShortcut(IConsoleConstants.ID_CONSOLE_VIEW);
		//
		layout.addShowViewShortcut(LiveExpressionsView.ID);
		layout.addShowViewShortcut(IPageLayout.ID_OUTLINE);
		layout.addShowViewShortcut(IPageLayout.ID_PROBLEM_VIEW);
		layout.addShowViewShortcut(IPageLayout.ID_RES_NAV);
		//
		layout.addNewWizardShortcut("org.erlide.ui.wizards.newproject");
		layout.addNewWizardShortcut("org.erlide.ui.wizards.ErlangSourceFile");
		layout.addNewWizardShortcut("org.eclipse.ui.wizards.new.folder"); //$NON-NLS-1$
		layout.addNewWizardShortcut("org.eclipse.ui.wizards.new.file"); //$NON-NLS-1$
		layout
				.addNewWizardShortcut("org.eclipse.ui.editors.wizards.UntitledTextFileWizard");//$NON-NLS-1$
	}

}
