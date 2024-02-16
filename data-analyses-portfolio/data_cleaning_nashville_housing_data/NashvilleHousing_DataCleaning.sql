Select *
From Housing_Data.dbo.NashvilleHousing_Data

----------------------------------------------------------------------------------------------------
--Standardize Date Format
Select SaleDateConverted, CONVERT(Date, SaleDate)
From Housing_Data.dbo.NashvilleHousing_Data

Update Housing_Data.dbo.NashvilleHousing_Data
SET SaleDate = CONVERT(Date, SaleDate)

ALTER TABLE Housing_Data.dbo.NashvilleHousing_Data
Add SaleDateConverted Date;

Update Housing_Data.dbo.NashvilleHousing_Data
SET SaleDateConverted = CONVERT(Date, SaleDate)

----------------------------------------------------------------------------------------------------
--Populate Property Address Data
Select *
From Housing_Data.dbo.NashvilleHousing_Data

Select a.ParcelID, a.PropertyAddress, b.ParcelID, b.PropertyAddress, ISNULL(a.PropertyAddress, b.PropertyAddress)
From Housing_Data.dbo.NashvilleHousing_Data a
JOIN Housing_Data.dbo.NashvilleHousing_Data b
	on a.ParcelID = b.ParcelID
	and a.[UniqueID ] <> b.[UniqueID ]
Where a.PropertyAddress is null

Update a
SET PropertyAddress = ISNULL(a.PropertyAddress, b.PropertyAddress)
From Housing_Data.dbo.NashvilleHousing_Data a
JOIN Housing_Data.dbo.NashvilleHousing_Data b
	on a.ParcelID = b.ParcelID
	and a.[UniqueID ] <> b.[UniqueID ]

----------------------------------------------------------------------------------------------------
--Breaking Address into (Address, City)
Select PropertyAddress
From Housing_Data.dbo.NashvilleHousing_Data

Select 
SUBSTRING(PropertyAddress, 1, CHARINDEX(',', PropertyAddress) - 1) as Address,
SUBSTRING(PropertyAddress, CHARINDEX(',', PropertyAddress) + 1, LEN(PropertyAddress))  as City
From Housing_Data.dbo.NashvilleHousing_Data

--Adding Address Column
ALTER TABLE Housing_Data.dbo.NashvilleHousing_Data
Add PropertySplitAddress Nvarchar(255);

Update Housing_Data.dbo.NashvilleHousing_Data
SET PropertySplitAddress = SUBSTRING(PropertyAddress, 1, CHARINDEX(',', PropertyAddress) - 1)

--Adding City Column
ALTER TABLE Housing_Data.dbo.NashvilleHousing_Data
Add PropertySplitCity Nvarchar(255);

Update Housing_Data.dbo.NashvilleHousing_Data
SET PropertySplitCity = SUBSTRING(PropertyAddress, CHARINDEX(',', PropertyAddress) + 1, LEN(PropertyAddress))

----------------------------------------------------------------------------------------------------
--Breaking Owner Address into (Address, City, State)
Select *
From Housing_Data.dbo.NashvilleHousing_Data

Select
PARSENAME(REPLACE(OwnerAddress, ',', '.'), 3), --Address
PARSENAME(REPLACE(OwnerAddress, ',', '.'), 2), --City
PARSENAME(REPLACE(OwnerAddress, ',', '.'), 1) --State
From Housing_Data.dbo.NashvilleHousing_Data

--Adding Address Column
ALTER TABLE Housing_Data.dbo.NashvilleHousing_Data
Add OwnerSplitAddress Nvarchar(255);

Update Housing_Data.dbo.NashvilleHousing_Data
SET OwnerSplitAddress = PARSENAME(REPLACE(OwnerAddress, ',', '.'), 3)

--Adding City Column
ALTER TABLE Housing_Data.dbo.NashvilleHousing_Data
Add OwnerSplitCity Nvarchar(255);

Update Housing_Data.dbo.NashvilleHousing_Data
SET OwnerSplitCity = PARSENAME(REPLACE(OwnerAddress, ',', '.'), 2)

--Adding State Column
ALTER TABLE Housing_Data.dbo.NashvilleHousing_Data
Add OwnerSplitState Nvarchar(255);

Update Housing_Data.dbo.NashvilleHousing_Data
SET OwnerSplitState = PARSENAME(REPLACE(OwnerAddress, ',', '.'), 1)

----------------------------------------------------------------------------------------------------
--Change Y and N to Yes and No in "Sold as Vacant"

Select Distinct(SoldAsVacant), COUNT(SoldAsVacant)
From Housing_Data.dbo.NashvilleHousing_Data
Group By SoldAsVacant
order by 2

Select SoldAsVacant,
Case When SoldAsVacant = 'Y' Then 'Yes'
	 When SoldAsVacant = 'N' Then 'No'
	 Else SoldAsVacant
	 End
From Housing_Data.dbo.NashvilleHousing_Data

Update Housing_Data.dbo.NashvilleHousing_Data
Set SoldAsVacant = Case When SoldAsVacant = 'Y' Then 'Yes'
						When SoldAsVacant = 'N' Then 'No'
						Else SoldAsVacant
						End

----------------------------------------------------------------------------------------------------
--Remove Duplicates (Not Standard Practice)
With RowNumCTE as(
Select *,
	ROW_NUMBER() Over(
	Partition By ParcelID,
				 PropertyAddress,
				 SalePrice,
				 SaleDate,
				 LegalReference
				 Order By
					UniqueID
					) row_num
From Housing_Data.dbo.NashvilleHousing_Data
)
Select *
From RowNumCTE
Where row_num > 1

----------------------------------------------------------------------------------------------------
--Delete Unused Columns
Select *
From Housing_Data.dbo.NashvilleHousing_Data

Alter Table Housing_Data.dbo.NashvilleHousing_Data
Drop Column OwnerAddress, TaxDistrict, PropertyAddress

Alter Table Housing_Data.dbo.NashvilleHousing_Data
Drop Column SaleDate