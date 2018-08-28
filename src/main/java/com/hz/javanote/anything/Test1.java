package com.hz.javanote.anything;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Test1 {
	public static void main(String args[]) throws Exception {
		List<BootDevice> cachedBootDevices=new ArrayList<>();
		BOSS boss1=new BOSS();
		boss1.setStatus(BootDevice.BootDeviceStatus.MISSING);
		boss1.setSlot(1);
		boss1.setSn("sn1");
		BOSS boss2=new BOSS();
		boss2.setSlot(2);
		boss2.setSn("sn2");
		BOSS boss3=new BOSS();
		boss3.setSlot(1);
		boss3.setSn("sn1");
//		SATADOM statdom1=new SATADOM();
		cachedBootDevices.add(boss1);
		cachedBootDevices.add(boss2);
		List<BootDevice> bootDevices=new ArrayList<>();
		bootDevices.add(boss3);
		removeMissingBootDevice(cachedBootDevices,bootDevices);
		System.out.println(cachedBootDevices);
		System.out.println(bootDevices);
		
	}

	static class BootDevice {

		public enum BootDeviceStatus {
			MISSING, NORMAL, ERROR;
		}

		private String sn;
		private BootDeviceStatus status=BootDeviceStatus.NORMAL;

		public BootDeviceStatus getStatus() {
			return status;
		}

		public void setStatus(BootDeviceStatus status) {
			this.status = status;
		}

		public String getSn() {
			return sn;
		}

		public void setSn(String sn) {
			this.sn = sn;
		}

	}

	static class BOSS extends BootDevice {
		private Integer slot;
		
		public BOSS() {
			
		}

		public Integer getSlot() {
			return slot;
		}

		public void setSlot(Integer slot) {
			this.slot = slot;
		}

		@Override
		public String toString() {
			return "BOSS [slot=" + slot + ", getSlot()=" + getSlot() + ", getStatus()=" + getStatus() + ", getSn()="
					+ getSn() + "]";
		}

	}

	static class SATADOM extends BootDevice {

	}

	static void removeMissingBootDevice(List<BootDevice> cachedBootDevices, List<BootDevice> bootDevices) {
		Map<Integer, String> bossSlotToSnMap = new HashMap<>();
		bossSlotToSnMap.putAll(bootDevices.stream().filter(bootDevice -> bootDevice instanceof BOSS)
				.map(bootDevice -> (BOSS) bootDevice).collect(Collectors.toMap(BOSS::getSlot, BOSS::getSn)));
		Iterator<BootDevice> iterCachedBootDevice = cachedBootDevices.listIterator();
		while (iterCachedBootDevice.hasNext()) {
			BootDevice oldBootDevice = iterCachedBootDevice.next();
			if (BootDevice.BootDeviceStatus.MISSING.equals(oldBootDevice.getStatus())) {
				if (oldBootDevice instanceof BOSS) {
					BOSS oldBoss = (BOSS) oldBootDevice;
					String newBossSn = bossSlotToSnMap.get(oldBoss.getSlot());
					if (newBossSn != null && !newBossSn.equals(oldBoss.getSn())) {
						iterCachedBootDevice.remove();
					}
				} else {
					iterCachedBootDevice.remove();
				}
			}
		}
	}

}
