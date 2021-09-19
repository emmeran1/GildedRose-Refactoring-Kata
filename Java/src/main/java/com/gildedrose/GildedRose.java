package com.gildedrose;

class GildedRose {
	Item[] items;

	public GildedRose(Item[] items) {
		this.items = items;
	}

	private int updateSingleQuality(Item item, boolean increaseOverTime, int qualityChange) {
		if (increaseOverTime) {
			return Math.min(item.quality + qualityChange, 50);
		} else {
			return Math.max(item.quality - isConjured(item) * qualityChange, 0);
		}
	}

	private int isConjured(Item item) {
		if (item instanceof ConjuredItem) {
			return 2;
		}
		return 1;
	}

	public void updateQuality() {
		for (int i = 0; i < items.length; i++) {
			Item item = items[i];
			switch (item.name) {
			case ItemName.AGED_BRIE:
				item.quality = updateSingleQuality(item, true, 1);
				break;
			case ItemName.BACKSTAGE_PASS:
				if (item.sellIn < 0) {
					item.quality = 0;
				} else if (item.sellIn <= 5) {
					item.quality = updateSingleQuality(item, true, 3);
				} else if (item.sellIn <= 10) {
					item.quality = updateSingleQuality(item, true, 2);
				} else {
					item.quality = updateSingleQuality(item, true, 1);
				}
				break;
			case ItemName.SULFURAS:
				break;
			default:
				if (item.sellIn < 0) {
					item.quality = updateSingleQuality(item, false, 2);
				} else {
					item.quality = updateSingleQuality(item, false, 1);
				}
			}
			item.sellIn--;
		}
	}
}