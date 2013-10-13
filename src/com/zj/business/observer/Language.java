package com.zj.business.observer;

import java.util.LinkedList;
import java.util.List;

public class Language {
	public static Language language;
	private List<LanguageObserver> obs;
	private LanguageType languageType;

	private Language() {
		obs = new LinkedList<LanguageObserver>();
	};

	public synchronized static Language getInstance() {
		if (language == null) {
			language = new Language();
		}
		return language;
	}

	public void setLanguage(LanguageType languageType) {
		this.languageType = languageType;
		notifyObservers();
	}

	public void addObserver(LanguageObserver vo) {
		if (vo == null) {
			throw new NullPointerException("vo is null");
		}
		if (!obs.contains(vo)) {
			obs.add(vo);
		}
	}

	public void notifyObservers() {
		for (LanguageObserver vo : obs) {
			vo.process(languageType);
		}
	}

	public void removeObserver(LanguageObserver vo) {
		obs.remove(vo);
	}

	public void removeObservers() {
		obs.clear();
	}

	public List<LanguageObserver> getAllObservers() {
		return obs;
	}
}
