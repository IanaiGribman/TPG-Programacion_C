package patrones.observer;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public abstract class ObservableAbstracto {
	private PropertyChangeSupport propertyChangeSupport;
	
	public ObservableAbstracto() {
		this.propertyChangeSupport = new PropertyChangeSupport(this);
	}
	
	public void addPropertyChangeListener(PropertyChangeListener listener) {
	    propertyChangeSupport.addPropertyChangeListener(listener);
	}
	
	public void removePropertyChangeListener(PropertyChangeListener listener) {
	    propertyChangeSupport.removePropertyChangeListener(listener);
	}
	
	protected void firePropertyChange(String propertyName, Object oldValue, Object newValue) {
	    propertyChangeSupport.firePropertyChange(propertyName, oldValue, newValue);
	}
	
	
}
