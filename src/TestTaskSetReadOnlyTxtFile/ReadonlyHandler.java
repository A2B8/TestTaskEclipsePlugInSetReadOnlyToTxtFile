package TestTaskSetReadOnlyTxtFile;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourceAttributes;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ITreeSelection;
import org.eclipse.ui.handlers.HandlerUtil;

public class ReadonlyHandler extends AbstractHandler {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		ISelection selection = HandlerUtil.getCurrentSelection(event);
		if (selection instanceof ITreeSelection) {
			Object[] firstElement = ((ITreeSelection) selection).toArray();
			for (Object iterable_element : firstElement) {
			if (iterable_element instanceof IAdaptable)	{
				IFile iFile = (IFile) ((IAdaptable) iterable_element).getAdapter(IFile.class);
				try {
					iFile.setResourceAttributes(setReadonlyTxt(iFile));
				} catch (CoreException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			}
		}		
		return null;
	}
	
	public static ResourceAttributes setReadonlyTxt(IResource resource) {
		  ResourceAttributes resourceAttributes = resource.getResourceAttributes();
		  if (resourceAttributes.isReadOnly()) {
			  resourceAttributes
			  .setReadOnly(false);
		  } else {
			  resourceAttributes
			  .setReadOnly(true);
		  }
		  return resourceAttributes;
		}
}
