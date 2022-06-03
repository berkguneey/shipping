package com.fleetmanagement.shipping.helper;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import com.fleetmanagement.shipping.constant.BagStatus;
import com.fleetmanagement.shipping.constant.CommonConstants;
import com.fleetmanagement.shipping.constant.PackageStatus;
import com.fleetmanagement.shipping.dto.BagDto;
import com.fleetmanagement.shipping.dto.BagRequestDto;
import com.fleetmanagement.shipping.dto.DeliveryDto;
import com.fleetmanagement.shipping.dto.IncorrectSentLogRequestDto;
import com.fleetmanagement.shipping.dto.PackageDto;
import com.fleetmanagement.shipping.dto.PackageRequestDto;
import com.fleetmanagement.shipping.service.BagService;
import com.fleetmanagement.shipping.service.IncorrectSentLogService;
import com.fleetmanagement.shipping.service.PackageService;
import com.fleetmanagement.shipping.util.BagValidation;
import com.fleetmanagement.shipping.util.PackageValidation;

@Component
@Qualifier("TransferCenterValidation")
public class TransferCenterValidationStrategy implements DeliveryPointValidationStrategy {

	private final PackageService packageService;
	private final BagService bagService;
	private final IncorrectSentLogService incorrectSentLogService;

	@Autowired
	public TransferCenterValidationStrategy(PackageService packageService, BagService bagService,
			IncorrectSentLogService incorrectSentLogService) {
		this.packageService = packageService;
		this.bagService = bagService;
		this.incorrectSentLogService = incorrectSentLogService;
	}

	@Override
	public List<DeliveryDto> unload(List<DeliveryDto> deliveryList, Long deliveryPointId) {

		deliveryList.forEach(delivery -> {
			String barcode = delivery.getBarcode();
			if (BagValidation.isValid(barcode)) {
				BagDto dBag = bagService.getBagByBarcode(barcode);
				if (!dBag.getDeliveryPoint().getId().equals(deliveryPointId)) {
					addLog(barcode, deliveryPointId, CommonConstants.DELIVER_TO_WRONG_POINT);
					return;
				}
				updateBagStatus(barcode);
				delivery.setState(BagStatus.UNLOADED.getState());
				List<PackageDto> packageList = packageService.getPackagesByBagId(dBag.getId());
				packageList.forEach(pckg -> {
					updatePackageStatus(pckg.getBarcode());
				});
			} else if (PackageValidation.isValid(barcode)) {
				PackageDto dPackage = packageService.getPackageByBarcode(barcode);
				if (!dPackage.getDeliveryPoint().getId().equals(deliveryPointId)) {
					addLog(barcode, deliveryPointId, CommonConstants.DELIVER_TO_WRONG_POINT);
					return;
				}
				if (ObjectUtils.isEmpty(dPackage.getBag())) {
					addLog(barcode, deliveryPointId, CommonConstants.TRANSFER_CENTER_ERR_MESSAGE); // do not need
					return;
				}
				updatePackageStatus(barcode);
				delivery.setState(PackageStatus.UNLOADED.getState());
				if(!ObjectUtils.isEmpty(dPackage.getBag())) {
					List<PackageDto> packageList = packageService.getPackagesByBagId(dPackage.getBag().getId());
					if(packageList.stream().allMatch(pckg -> pckg.getState() == 4)) {
						updateBagStatus(dPackage.getBag().getBarcode());
					}
				}
			}
		});
		return deliveryList;
	}
	
	private void updatePackageStatus(String barcode) {
		PackageRequestDto packageRequest = new PackageRequestDto();
		packageRequest.setState(PackageStatus.UNLOADED.getState());
		packageService.update(barcode, packageRequest);
	}

	private void updateBagStatus(String barcode) {
		BagRequestDto bagRequest = new BagRequestDto();
		bagRequest.setState(BagStatus.UNLOADED.getState());
		bagService.update(barcode, bagRequest);
	}
	
	private void addLog(String barcode, Long deliveryPointId, String message) {
		IncorrectSentLogRequestDto incorrectSentLogRequest = new IncorrectSentLogRequestDto();
		incorrectSentLogRequest.setBarcode(barcode);
		incorrectSentLogRequest.setDeliveryPointId(deliveryPointId);
		incorrectSentLogRequest.setMessage(message);
		incorrectSentLogService.insert(incorrectSentLogRequest);
	}

}
