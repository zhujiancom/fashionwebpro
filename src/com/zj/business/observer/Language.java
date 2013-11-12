package com.zj.business.observer;

import java.util.LinkedList;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service("language")
@Scope("prototype")
public class Language {
	private List<LanguageObserver> obs;
	private LanguageType languageType;

	public Language() {
		obs = new LinkedList<LanguageObserver>();
	};

	public void setLanguage(LanguageType languageType) {
		this.languageType = languageType;
		notifyObservers();
	}

	public synchronized void addObserver(LanguageObserver vo) {
		if (vo == null) {
			throw new NullPointerException("vo is null");
		}
		if (!obs.contains(vo)) {
			obs.add(vo);
		}
	}

	public synchronized void notifyObservers() {
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
