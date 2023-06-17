package TestTaskSetReadOnlyTxtFile;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ITreeSelection;
import org.eclipse.ui.handlers.HandlerUtil;

public class ReadonlyHandler extends AbstractHandler {

	@SuppressWarnings("deprecation")
	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		ISelection selection = HandlerUtil
				.getCurrentSelection(event);
		if (selection instanceof ITreeSelection) {
			Object firstElement = ((ITreeSelection) selection)
					.getFirstElement();
			if (firstElement instanceof IAdaptable)	{
				IFile iFile = (IFile) ((IAdaptable) firstElement)
						.getAdapter(IFile.class);
				if(iFile.isReadOnly()) {
					iFile.setReadOnly(false);
				} else {
					iFile.setReadOnly(true);
				}
			}
		}		
		return null;
	}
}
