package org.cryptomator.ui.unlock;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import dagger.multibindings.IntoMap;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.cryptomator.ui.common.FXMLLoaderFactory;
import org.cryptomator.ui.common.FxController;
import org.cryptomator.ui.common.FxControllerKey;
import org.cryptomator.ui.model.Vault;

import javax.inject.Provider;
import java.util.Map;

@Module
abstract class UnlockModule {

	@Provides
	@UnlockWindow
	@UnlockScoped
	static FXMLLoaderFactory provideFxmlLoaderFactory(Map<Class<? extends FxController>, Provider<FxController>> factories) {
		return new FXMLLoaderFactory(factories);
	}

	@Provides
	@UnlockWindow
	@UnlockScoped
	static Stage provideStage() {
		Stage stage = new Stage();
		stage.setMinWidth(300);
		stage.setMinHeight(200);
		stage.initModality(Modality.APPLICATION_MODAL);
		return stage;
	}
	
	@Provides
	@UnlockWindow
	@UnlockScoped
	static ReadOnlyObjectProperty<Vault> provideVaultProperty(@UnlockWindow Vault vault) {
		return new SimpleObjectProperty<>(vault);
	}

	// ------------------

	@Binds
	@IntoMap
	@FxControllerKey(UnlockController.class)
	abstract FxController bindUnlockController(UnlockController controller);


}
