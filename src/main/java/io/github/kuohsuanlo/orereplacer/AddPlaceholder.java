package io.github.kuohsuanlo.orereplacer;

import java.text.DecimalFormat;

import javax.swing.text.NumberFormatter;

import org.bukkit.entity.Player;

import me.clip.placeholderapi.external.EZPlaceholderHook;

public class AddPlaceholder extends EZPlaceholderHook{
	private OreReplacerPlugin oplugin;

	public AddPlaceholder(OreReplacerPlugin instance) {
		super(instance, "orereplacer");
		// TODO Auto-generated constructor stub
		oplugin = instance;
	}

	@Override
	public String onPlaceholderRequest(Player p, String identifier) {
		if (identifier.equals("increasing_constant")) {
			return oplugin.PROBABILITY_INCREASING_CONSTANT+"";
	    }
		return "";
	}


}