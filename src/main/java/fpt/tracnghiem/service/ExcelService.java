package fpt.tracnghiem.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fpt.tracnghiem.entity.Anh;
import fpt.tracnghiem.entity.CauHoi;
import fpt.tracnghiem.entity.DeThi;
import fpt.tracnghiem.entity.PhuongAn;
@Service
public class ExcelService {
	@Autowired
	CauHoiService cauHoiService;
	@Autowired 
	DeThiService deThiService;
	@Transactional
	public void upDuLieu(Workbook workbook,Integer idDe) {
		for (Sheet sheet : workbook) {
			for (Row row : sheet) {

				if (row.getRowNum() != 0) {
					String noiDungCauHoi,giaiThich,pa1,pa2,pa3,pa4;
					Boolean correct1,correct2,correct3,correct4;
					
					// kiem tra cell co null khong
					if(row.getCell(0)==null) {
						throw new NullPointerException("lỗi");
					}
					else {
						noiDungCauHoi =row.getCell(0).getStringCellValue();
					}
					
					if(row.getCell(1)==null) {
						throw new NullPointerException("lỗi");
					}
					else {
						giaiThich =row.getCell(1).getStringCellValue();
					}
					
					if(row.getCell(2)==null) {
						throw new NullPointerException("lỗi");
					}
					else {
						pa1 =row.getCell(2).getStringCellValue();
					}
					//correct1
					if(row.getCell(3)==null) {
						throw new NullPointerException("lỗi");
					}
					else {
						correct1 =(row.getCell(3).getNumericCellValue()==1f)?true:false;
					}
					//pa2
					if(row.getCell(4)==null) {
						throw new NullPointerException("lỗi");
					}
					else {
						pa2 =row.getCell(4).getStringCellValue();
					}
					
					//correct2
					if(row.getCell(5)==null) {
						throw new NullPointerException("lỗi");
					}
					else {
						correct2 =(row.getCell(5).getNumericCellValue()==1f)?true:false;
					}
					
					//pa3
					if(row.getCell(6)==null) {
						throw new NullPointerException("lỗi");
					}
					else {
						pa3 =row.getCell(6).getStringCellValue();
					}
					
					//correct3
					if(row.getCell(7)==null) {
						throw new NullPointerException("lỗi");
					}
					else {
						correct3 =(row.getCell(7).getNumericCellValue()==1f)?true:false;
					}
					
					//pa4
					if(row.getCell(8)==null) {
						throw new NullPointerException("lỗi");
					}
					else {
						pa4 =row.getCell(8).getStringCellValue();
					}
					//correct 4
					
					if(row.getCell(9)==null) {
						throw new NullPointerException("lỗi");
					}
					else {
						correct4 =(row.getCell(9).getNumericCellValue()==1f)?true:false;
					}
					
					System.out.println(noiDungCauHoi + " " + giaiThich+ " "+ pa1 + pa2+ pa3 + pa4 +correct1+correct2+correct3+correct4);


					//up len db
					CauHoi cauHoi = new CauHoi();
					cauHoi.setGiaiThich(giaiThich);
					cauHoi.setNoiDung(noiDungCauHoi);
					List<PhuongAn> listPhuongAn = new ArrayList<PhuongAn>();
					listPhuongAn.add(new PhuongAn(pa1,correct1,cauHoi));
					listPhuongAn.add(new PhuongAn(pa2,correct2,cauHoi));
					listPhuongAn.add(new PhuongAn(pa3,correct3,cauHoi));
					listPhuongAn.add(new PhuongAn(pa4,correct4,cauHoi));
					
					cauHoiService.save(cauHoi, listPhuongAn, null,idDe);
				}
			}
		}
	}
}
