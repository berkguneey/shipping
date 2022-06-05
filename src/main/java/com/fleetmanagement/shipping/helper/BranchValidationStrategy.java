package com.fleetmanagement.shipping.helper;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import com.fleetmanagement.shipping.constant.CommonConstants;
import com.fleetmanagement.shipping.constant.PackageStatus;
import com.fleetmanagement.shipping.dto.DeliveryDto;
import com.fleetmanagement.shipping.dto.PackageDto;
import com.fleetmanagement.shipping.dto.PackageRequestDto;
import com.fleetmanagement.shipping.service.IncorrectSentLogService;
import com.fleetmanagement.shipping.service.PackageService;
import com.fleetmanagement.shipping.util.PackageValidation;

@Component
@Qualifier("BranchValidation")
public class BranchValidationStrategy implements DeliveryPointValidationStrategy {

	private final PackageService packageService;
	private final IncorrectSentLogService incorrectSentLogService;

	@Autowired
	public BranchValidationStrategy(PackageService packageService, IncorrectSentLogService incorrectSentLogService) {
		this.packageService = packageService;
		this.incorrectSentLogService = incorrectSentLogService;
	}

	@Override
	public List<DeliveryDto> unload(List<DeliveryDto> deliveryList, Long deliveryPointId) {

		deliveryList.forEach(delivery -> {
			String barcode = delivery.getBarcode();
			if (!PackageValidation.isValid(barcode)) {
				addLog(barcode, deliveryPointId, CommonConstants.BRANCH_ERR_MESSAGE); // do not need
				return;
			}
			PackageDto dPackage = packageService.getPackageByBarcode(barcode);
			if (!dPackage.getDeliveryPoint().getId().equals(deliveryPointId)) {
				addLog(barcode, deliveryPointId, CommonConstants.DELIVER_TO_WRONG_POINT);
				return;
			}
			if (!ObjectUtils.isEmpty(dPackage.getBag())) {
				addLog(barcode, deliveryPointId, CommonConstants.BRANCH_ERR_MESSAGE); // do not need
				return;
			}
			unloadPackage(barcode);
			delivery.setState(PackageStatus.UNLOADED.getState());

		});
		return deliveryList;
	}

	private void unloadPackage(String barcode) {
		PackageRequestDto packageRequest = new PackageRequestDto();
		packageRequest.setState(PackageStatus.UNLOADED.getState());
		packageService.update(barcode, packageRequest);
	}

	@Override
	public IncorrectSentLogService getIncorrectSentLogService() {
		return incorrectSentLogService;
	}

}
